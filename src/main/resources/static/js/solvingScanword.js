document.addEventListener('DOMContentLoaded', () => {
    fetch("scanword/4508925f-50e3-48df-9580-d3bfc3fc3cc8")
        .then(response => response.json())
        .then(scanword => {
            loadScanword(scanword);
        });
});
let isHorizontal = true;

function loadScanword(scanword) {

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
            // Убираем подсветку со всех ячеек
            document.querySelectorAll('.cell').forEach(cell => {
                if (cell.getAttribute("word-id") === null) {
                    cell.style.backgroundColor = cell.getAttribute("horizontal-word-id") === id ||
                    cell.getAttribute("vertical-word-id") === id ? "yellow" : "white";
                }
            });
            isHorizontal = scanwordDivElement.children[item].getAttribute("horizontally");
            for (let i = 0; i < scanwordDivElement.children.length; i++) {
                if (scanwordDivElement.children[i].getAttribute("horizontal-word-id") === id ||
                    scanwordDivElement.children[i].getAttribute("vertical-word-id") === id) {
                    scanwordDivElement.children[i].focus();
                    break;
                }
            }
        });

    });
    for (let i = 0; i < m * n; i++) {
        if (!tasks[i]) {
            scanwordDivElement.children[i].addEventListener('input', function (e) {
                e.target.value = e.target.value.toUpperCase();
                if (e.target.getAttribute("horizontal-word-id") !== null) {
                    const id = e.target.getAttribute("horizontal-word-id");
                    let wordLength = scanword.content[tasksPlaces[id]].word.length;
                    let pos = 0;
                    while (scanwordDivElement.children[pos].getAttribute("horizontal-word-id") !== id)
                        pos++
                    const start = pos;
                    let inputWord = "";
                    while (scanwordDivElement.children[pos].value !== "" && inputWord.length < wordLength) {
                        inputWord += scanwordDivElement.children[pos++].value;
                    }
                    let color = "white";
                    if (inputWord.length === scanword.content[tasksPlaces[id]].word.length)
                        color = "red"
                    if (inputWord === scanword.content[tasksPlaces[id]].word.toUpperCase())
                        color = "green"
                    for (let k = start; k < start + wordLength; k++) {
                        scanwordDivElement.children[k].style.backgroundColor = color;
                        if (color === "green")
                            scanwordDivElement.children[k].readOnly = true;
                    }
                }
            });
        }
    }
}

function findAndMarkWord(word, id, m, n, scanword, scanwordDivElement) {
    // ������� ��� ������ �� �����������
    function findHorizontal() {
        for (let i = 0; i < m; i++) {
            for (let j = 0; j <= n - word.length; j++) {
                let slice = scanword.content.slice(i * n + j, i * n + j + word.length).map(item => item.letter).join('');
                if (slice === word && (j + word.length + 1 > n ||
                    scanwordDivElement.children[i * n + j + word.length].getAttribute("word-id") != null)) {
                    for (let k = 0; k < word.length; k++) {
                        scanwordDivElement.children[i * n + j + k].setAttribute("horizontal-word-id", id);
                        if (k < word.length - 1) {
                            scanwordDivElement.children[i * n + j + k].addEventListener("keyup", (event) => {
                                if (isHorizontal === "true") {
                                    //event.target.value = event.target.value.toUpperCase();
                                    if (event.key !== 'Backspace')
                                        scanwordDivElement.children[i * n + j + k + 1].focus();
                                    else if (i * n + j + k - 1 >= 0)
                                        scanwordDivElement.children[i * n + j + k - 1].focus();
                                }
                            });
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    // ������� ��� ������ �� ���������
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
                        if (k < word.length - 1) {
                            scanwordDivElement.children[(i + k) * n + j].addEventListener("keyup", (event) => {
                                if (isHorizontal === "false") {
                                    if (event.key !== 'Backspace')
                                        scanwordDivElement.children[(i + k + 1) * n + j].focus();
                                    else if (i + k - 1 >= 0)
                                        scanwordDivElement.children[(i + k - 1) * n + j].focus();
                                }
                            });
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    // ����� �����
    if (findVertical())
        return false;
    if (findHorizontal())
        return true;
    alert("Нет найдено слово " + word);
    return true;
}

async function processItems(tasksPlaces, scanword, scanwordDivElement, m, n) {
    for (const item of tasksPlaces) {
        let word;
        if (scanword.content[item].taskId === null) {
            word = scanword.content[item].word;
            word = word.toUpperCase();
        } else {
            let type;
            if (scanword.content[item].taskType === "IMAGE") {
                type = "image";
            } else if (scanword.content[item].taskType === "MELODY") {
                type = "melody";
            } else {
                alert("No Image or Melody or Verbal");
                continue; // ���������� �������� ���� ��� �� �������������
            }

            const request = `${type}/${scanword.content[item].taskId}`;

            try {
                const response = await fetch(request);
                const dataBase = await response.json();
                word = dataBase.answer.toUpperCase(); // ���������� ������ � �������� ��������
            } catch (error) {
                console.error("������ ��� ���������� �������:", error);
                continue; // ���������� �������� ��� ������
            }
        }
        let id = scanwordDivElement.children[item].getAttribute("word-id");
        const isHorizontal = findAndMarkWord(word, id, m, n, scanword, scanwordDivElement);
        if (isHorizontal)
            scanwordDivElement.children[item].setAttribute("horizontally", "true");
        else
            scanwordDivElement.children[item].setAttribute("horizontally", "false");
    }
}