document.addEventListener('DOMContentLoaded', () => {
    const scanwordId = localStorage.getItem("scanword-id");
    fetch("scanword/" + scanwordId)
        .then(response => response.json())
        .then(scanword => {
            loadDraft(scanword)
        });
});
let isHorizontal = true;
let CurrentId = "";
let hintCount = 3;

function loadDraft(scanword) {
    const draftId = localStorage.getItem("draft-scanword-id");
    fetch("draft-scanword/" + draftId)
        .then(response => response.json())
        .then(draft => {
            loadDictionary(scanword, draft)
        });
}

function loadDictionary(scanword, draft) {
    const dictionaryId = scanword.dictionaryId;
    fetch("create_api/" + dictionaryId)
        .then(response => response.json())
        .then(dataAll => {
            loadScanword(scanword, draft, dataAll)
        });
}

function loadScanword(scanword, draft, dataAll) {
    const title = document.getElementById("title");
    const container = document.getElementById('crosswordContainer');
    const scanwordDivElement = document.createElement('div');
    const m = scanword.height; // ���������� �����
    const n = scanword.width; // ���������� ��������
    let wordCount = 0;
    let tasks = [m * n];
    let tasksPlaces = [wordCount];
    let index = 0;
    const draftContent = draft.content;
    scanwordDivElement.className = 'scanwordDivElement';
    title.innerText = scanword.name;
    scanwordDivElement.style.gridTemplateColumns = "repeat(" + n + ", 30px)";
    scanword.content.forEach(item => {
        const cell = document.createElement('input');
        cell.type = 'text';
        cell.className = 'cell';
        cell.maxLength = 1;
        if (item.task) {
            switch (item.taskType) {
                case 'VERBAL':
                    cell.value = '📄';
                    cell.title = "Текстовое задание";
                    break;
                case 'IMAGE':
                    cell.value = '🖼️';
                    cell.title = "Задание с картинкой";
                    break;
                case 'MELODY':
                    cell.title = "Задание с мелодией";
                    cell.value = '🎵';
                    break;
            }
            wordCount++;
            tasks[index] = true;
        }
        scanwordDivElement.appendChild(cell);
        index++;
    });
    container.appendChild(scanwordDivElement);
    let j = 0;
    for (let i = 0; i < m * n; i++) {
        if (tasks[i]) {
            scanwordDivElement.children[i].readOnly = true;
            scanwordDivElement.children[i].style.cursor = 'pointer';
            scanwordDivElement.children[i].style.fontFamily = 'cursive';
            scanwordDivElement.children[i].style.color = 'white';
            scanwordDivElement.children[i].style.backgroundColor = 'black';
            scanwordDivElement.children[i].setAttribute("word-id", j.toString());
            tasksPlaces[j++] = i;
        }
    }
    processItems(tasksPlaces, scanword, scanwordDivElement, m, n, tasksPlaces);
    //обработчик клика на задание
    tasksPlaces.forEach(item => {
        const id = scanwordDivElement.children[item].getAttribute("word-id");
        // Добавляем обработчик клика на ячейки с заданиями
        scanwordDivElement.children[item].addEventListener("click", () => {
            let word = scanword.content[item].word;
            let wordIndex = 0, definition, image, melody;
            definition = document.getElementById("definition");
            image = document.getElementById("image");
            melody = document.getElementById("melody");
            switch (scanword.content[item].taskType) {
                case 'VERBAL':
                    image.style.display = "none";
                    melody.src = "";
                    melody.controls = false;
                    while (dataAll.data.dictionaryData[wordIndex].word !== word)
                        wordIndex++;
                    definition.innerText = dataAll.data.dictionaryData[wordIndex].definition;
                    break;
                case 'IMAGE':
                    image.style.display = "block";
                    melody.src = "";
                    melody.controls = false;
                    while (dataAll.data.images[wordIndex].answer !== word)
                        wordIndex++;
                    image.src = dataAll.data.images[wordIndex].base64Image;
                    definition.innerText = dataAll.data.images[wordIndex].question;
                    break;
                case 'MELODY':
                    image.style.display = "none";
                    while (dataAll.data.melodies[wordIndex].answer !== word)
                        wordIndex++;
                    definition.innerText = dataAll.data.melodies[wordIndex].question;
                    const base64Melody = dataAll.data.melodies[wordIndex].melody;
                    // Преобразуем base64 в бинарные данные
                    const byteCharacters = atob(base64Melody);
                    const byteNumbers = new Uint8Array(byteCharacters.length);
                    for (let i = 0; i < byteCharacters.length; i++) {
                        byteNumbers[i] = byteCharacters.charCodeAt(i);
                    }
                    const audioBlob = new Blob([byteNumbers], {type: 'audio/mpeg'}); // Укажите правильный тип аудио
                    const audioURL = URL.createObjectURL(audioBlob);
                    melody.controls = true;
                    melody.src = audioURL;
                    break;
            }
            document.querySelectorAll('.cell-allocated').forEach(cell => {
                cell.className = "cell";
            });
            document.querySelectorAll('.cell').forEach(cell => {
                if (cell.getAttribute("horizontal-word-id") === id ||
                    cell.getAttribute("vertical-word-id") === id) {
                    cell.className = "cell-allocated";
                }
            });
            isHorizontal = scanwordDivElement.children[item].getAttribute("horizontally");
            //выделяем 1 букву слова на сканворде, соответствующую заданию
            let pos = 0;
            while (scanwordDivElement.children[pos].getAttribute("horizontal-word-id") !== id &&
            scanwordDivElement.children[pos].getAttribute("vertical-word-id") !== id)
                pos++;
            scanwordDivElement.children[pos].focus();
            CurrentId = id;
        });

    });
    for (let i = 0; i < m * n; i++) {
        if (!tasks[i]) {
            // добавляем обработчик ввода на клетки с буквами
            scanwordDivElement.children[i].addEventListener('input', function (e) {
                e.target.value = e.target.value.toUpperCase();
                const wordLength = scanword.content[tasksPlaces[CurrentId]].word.length;
                let inputWord = "";
                if (e.target.className === "cell-allocated") {
                    let pos = 0;
                    while (scanwordDivElement.children[pos].getAttribute("horizontal-word-id") !== CurrentId &&
                    scanwordDivElement.children[pos].getAttribute("vertical-word-id") !== CurrentId)
                        pos++;
                    for (let k = 0; k < wordLength; k++) {
                        const nextIndex = isHorizontal === "true" ? pos + k : (k * n) + pos;
                        if (scanwordDivElement.children[nextIndex].value !== "")
                            inputWord += scanwordDivElement.children[nextIndex].value;
                    }
                }
                if (inputWord.length === wordLength) {
                    document.querySelectorAll('.cell-allocated').forEach(cell => {
                        if (inputWord === scanword.content[tasksPlaces[CurrentId]].word.toUpperCase()) {
                            cell.className = "cell-solved";
                            cell.readOnly = true;
                        } else
                            cell.className = "cell-wrong";
                    });
                } else if (e.target.className === "cell-wrong" && e.target.value === "") {
                    document.querySelectorAll('.cell-wrong').forEach((cell) => {
                        if (cell.getAttribute("horizontal-word-id") === CurrentId || cell.getAttribute("vertical-word-id") === CurrentId)
                            cell.className = "cell-allocated";
                    });
                }
                if (m * n - wordCount === document.querySelectorAll('.cell-solved').length) {
                    alert("Сканворд полностью разгадан. Поздравляем!");
                }
            });
        }
    }
    let hint = document.getElementById("hint");
    hint.addEventListener("click", function () {
        let isDone;
        if (hintCount !== 0) {
            isDone = false;
            document.querySelectorAll('.cell-allocated').forEach(cell => {
                if (!isDone)
                    isDone = true;
                const index = Array.from(scanwordDivElement.children).indexOf(cell);
                cell.value = scanword.content[index].letter;
                cell.className = "cell-solved";
                cell.readOnly = true;
            });
            if (isDone)
                hintCount--;
            else
                alert("Никакое слово не выделено. Выделите слово, чтобы использовать подсказку");
        } else {
            alert("Подсказки закончились");
            hint.style.display = "none";
        }
    });
}

function findAndMarkWord(word, id, m, n, scanword, scanwordDivElement, taskPlaces) {
    function findHorizontal() {
        for (let i = 0; i < m; i++) {
            for (let j = 0; j <= n - word.length; j++) {
                let slice = scanword.content.slice(i * n + j, i * n + j + word.length).map(item => item.letter).join('');
                if (slice === word && (j + word.length + 1 > n ||
                    scanwordDivElement.children[i * n + j + word.length].getAttribute("word-id") != null)) {
                    for (let k = 0; k < word.length; k++) {
                        scanwordDivElement.children[i * n + j + k].setAttribute("horizontal-word-id", id);
                        // добавляем обработчик горизонтального движения
                        scanwordDivElement.children[i * n + j + k].addEventListener("keyup", (event) => {
                            if (isHorizontal === "true") {
                                if (event.key !== 'Backspace' && k < word.length - 1)
                                    scanwordDivElement.children[i * n + j + k + 1].focus();
                                if (event.key === 'Backspace' && (j + k) % n !== 0 && scanwordDivElement.children[i * n + j + k - 1].getAttribute("word-id") === null)
                                    scanwordDivElement.children[i * n + j + k - 1].focus();
                            }
                        });
                        scanwordDivElement.children[i * n + j + k].addEventListener("click", (e) => {
                            taskPlaces.forEach(item => {
                                if (scanwordDivElement.children[item].getAttribute("word-id") === e.target.getAttribute("horizontal-word-id"))
                                    scanwordDivElement.children[item].click();
                            });
                            e.target.focus();
                        });
                    }
                    return true;
                }
            }
        }
        return false;
    }

    function findVertical() {
        for (let j = 0; j < n; j++) {
            for (let i = 0; i <= m - word.length; i++) {
                let slice = '';
                for (let k = 0; k < word.length; k++) {
                    slice += scanword.content[(i + k) * n + j].letter;
                }
                if (slice === word && ((i + word.length) * n + j >= scanwordDivElement.children.length ||
                    scanwordDivElement.children[(i + word.length) * n + j].getAttribute("word-id") != null)) {
                    for (let k = 0; k < word.length; k++) {
                        scanwordDivElement.children[(i + k) * n + j].setAttribute("vertical-word-id", id);
                        // добавляем обработчик вертикального движения
                        scanwordDivElement.children[(i + k) * n + j].addEventListener("keyup", (event) => {
                            if (isHorizontal === "false") {
                                if (event.key !== 'Backspace' && k < word.length - 1)
                                    scanwordDivElement.children[(i + k + 1) * n + j].focus();
                                if (event.key === 'Backspace' && i + k - 1 >= 0 && scanwordDivElement.children[(i + k - 1) * n + j].getAttribute("word-id") === null)
                                    scanwordDivElement.children[(i + k - 1) * n + j].focus();
                            }
                        });
                        let type = "dblclick";
                        if ((j % n === 0 || scanwordDivElement.children[(i + k) * n + j - 1].getAttribute("word-id") !== null)
                            && (j % n === n - 1 || scanwordDivElement.children[(i + k) * n + j + 1].getAttribute("word-id") !== null))
                            type = "click";
                        scanwordDivElement.children[(i + k) * n + j].addEventListener(type, (e) => {
                            taskPlaces.forEach(item => {
                                if (scanwordDivElement.children[item].getAttribute("word-id") === e.target.getAttribute("vertical-word-id"))
                                    scanwordDivElement.children[item].click();
                            });
                            e.target.focus();
                        });
                    }
                    return true;
                }
            }
        }
        return false;
    }

    if (findVertical())
        return false;
    if (findHorizontal())
        return true;
    alert("Нет найдено слово " + word);
    return true;
}

function processItems(tasksPlaces, scanword, scanwordDivElement, m, n) {
    for (const item of tasksPlaces) {
        let word;
        word = scanword.content[item].word;
        word = word.toUpperCase();
        let id = scanwordDivElement.children[item].getAttribute("word-id");
        const isHorizontal = findAndMarkWord(word, id, m, n, scanword, scanwordDivElement, tasksPlaces);
        if (isHorizontal)
            scanwordDivElement.children[item].setAttribute("horizontally", "true");
        else
            scanwordDivElement.children[item].setAttribute("horizontally", "false");
    }
}