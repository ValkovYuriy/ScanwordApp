document.addEventListener('DOMContentLoaded', () => {
    fetch("scanword/4508925f-50e3-48df-9580-d3bfc3fc3cc8")
        .then(response => response.json())
        .then(scanword => {
            loadDictionary(scanword)
        });

});
let isHorizontal = true;
let CurrentId = "";

function loadDictionary(scanword) {
    const dictionaryId = scanword.dictionaryId;
    fetch("create_api/" + dictionaryId)
        .then(response => response.json())
        .then(dataAll => {
            loadScanword(scanword, dataAll)
        });
}

function loadScanword(scanword, dataAll) {

    const title = document.getElementById("title");
    const container = document.getElementById('crosswordContainer');
    const scanwordDivElement = document.createElement('div');
    const m = scanword.height; // ���������� �����
    const n = scanword.width; // ���������� ��������
    let wordCount = 0;
    let tasks = [m * n];
    let tasksPlaces = [wordCount];
    let index = 0;
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
                    break;
                case 'IMAGE':
                    cell.value = '🖼️';
                    break;
                case 'MELODY':
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
    tasksPlaces.forEach(item => {
        const id = scanwordDivElement.children[item].getAttribute("word-id");
        // Добавляем обработчик клика на ячейки с заданиями
        scanwordDivElement.children[item].addEventListener("click", () => {
            let word = scanword.content[item].word;
            let wordIndex = 0;
            definition = document.getElementById("definition");
            image = document.getElementById("image");
            melody = document.getElementById("melody");
            switch (scanword.content[item].taskType) {
                case 'VERBAL':
                    image.src = "";
                    melody.src = "";
                    melody.controls = false;
                    while (dataAll.data.dictionaryData[wordIndex].word !== word)
                        wordIndex++;
                    definition.innerText = dataAll.data.dictionaryData[wordIndex].definition;
                    break;
                case 'IMAGE':
                    melody.src = "";
                    melody.controls = false;
                    while (dataAll.data.images[wordIndex].answer !== word)
                        wordIndex++;
                    image.src = dataAll.data.images[wordIndex].base64Image;
                    definition.innerText = dataAll.data.images[wordIndex].question;
                    break;
                case 'MELODY':
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
            });
        }
    }
}

function findAndMarkWord(word, id, m, n, scanword, scanwordDivElement) {
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
        const isHorizontal = findAndMarkWord(word, id, m, n, scanword, scanwordDivElement);
        if (isHorizontal)
            scanwordDivElement.children[item].setAttribute("horizontally", "true");
        else
            scanwordDivElement.children[item].setAttribute("horizontally", "false");
    }
}