document.addEventListener('DOMContentLoaded', () => {
    fetch("scanword/9fd2f3cf-a240-4c41-887d-60ea7cdef02e")
        .then(response => response.json())
        .then(scanword => {
            loadScanword(scanword);
        });
});

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
        if (!item.task) {
            cell.addEventListener('input', function (e) {
                e.target.value = e.target.value.toUpperCase();
            });
        } else {
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
            scanwordDivElement.children[i].disabled = true;
            scanwordDivElement.children[i].style.fontFamily = 'cursive';
            scanwordDivElement.children[i].style.color = 'white';
            scanwordDivElement.children[i].style.backgroundColor = 'black';
            scanwordDivElement.children[i].setAttribute("word-id", j.toString());
            tasksPlaces[j++] = i;
        }
    }
    processItems(tasksPlaces, scanword, scanwordDivElement, m, n);
    /*
    tasksPlaces.forEach(item => {
        let word;
        if (scanword.content[item].taskId === null) {
            word = scanword.content[item].word;
        } else {
            let type;
            if (scanword.content[item].taskType === "IMAGE")
                type = "image";
            else {
                if (scanword.content[item].taskType === "MELODY")
                    type = "melody";
                else
                    alert("No Image or Melody or Verbal");
            }
            const request = type + "/" + scanword.content[item].taskId;
            fetch(request)
                .then(response => response.json())
                .then(dataBase => {
                    word = dataBase.answer;
                    word = word.toUpperCase();
                });
        }
        console.log(word);
        let id = scanwordDivElement.children[item].getAttribute("word-id");
        findAndMarkWord(word, id, m, n, scanword, scanwordDivElement);
    });*/
}

function findAndMarkWord(word, id, m, n, scanword, scanwordDivElement) {
    // ������� ��� ������ �� �����������
    function findHorizontal() {
        for (let i = 0; i < m; i++) {
            for (let j = 0; j <= n - word.length; j++) {
                let slice = scanword.content.slice(i * n + j, i * n + j + word.length).map(item => item.letter).join('');
                if (slice === word) {
                    for (let k = 0; k < word.length; k++) {
                        scanwordDivElement.children[i * n + j + k].setAttribute("horizontal-word-id", id);
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
                if (slice === word) {
                    for (let k = 0; k < word.length; k++) {
                        scanwordDivElement.children[(i + k) * n + j].setAttribute("vertical-word-id", id);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    // ����� �����
    if (!findHorizontal() && !findVertical()) {
        alert("�����" + word + " �� �������.");
    }
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

        console.log(word);
        let id = scanwordDivElement.children[item].getAttribute("word-id");
        findAndMarkWord(word, id, m, n, scanword, scanwordDivElement);
    }
}