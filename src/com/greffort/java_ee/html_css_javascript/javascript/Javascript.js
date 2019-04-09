/*
* Для текстового поля ввода имени сотрудника создайте скрипт, выводящий в поле ввода надпись «например, Иванов».
* При получении полем ввода фокуса (установка курсора) надпись должна исчезать, а при потере фокуса – появляться.
* */
function onFocus() {
    if (document.getElementById("textEName").value === 'Например, Иванов') {
        document.getElementById("textEName").value = '';
    }
}

function onBlur() {
    if (document.getElementById("textEName").value === '') {
        document.getElementById("textEName").value = 'Например, Иванов';
    }
}

/*
* Для текстового поля ввода и кнопки подтверждения ввода номера создайте скрипт, проверяющий,
* является ли введенная последовательность числом.
* Если введенная последовательность символов не является числом, необходимо выводить сообщение о
* необходимости ввода числа и кнопкой «Ок» закрытия сообщения.
* Для выполнения задания можно воспользоваться регулярными выражениями, или функциями parseInt() и isNaN().
* */
function checkInputTextEmpNo() {
    var value = document.getElementById("textEmpNo").value;
    var reg = /^\d+$/;
    if (reg.test(value)) {

    } else {
        alert("необходимо выводить сообщение о\n" +
            " необходимости ввода числа");
    }
}

/*
* Для гиперссылки удаления информации о сотруднике в таблице на странице создайте скрипт
* вывода окна подтверждения удаления с двумя кнопками «Удалить» и «Отмена».
* При нажатии кнопки «Удалить» необходимо скрывать соответствующую строку в таблице.
*/
function checkDelete() {
    if (confirm("Вы уверены что хотите удалить пользователя?")) {
        var table = document.getElementById("table");
        var row = table.getElementsByTagName('tr');
        for (var j = 0; j < row.length; j++) {
            var cells = row[j].getElementsByTagName('td');
            if (cells.length > 0) {
                var chk = cells[0].getElementsByTagName("input")[0].checked;
                if (chk) {
                    row[j].style.display = "none";
                    // table.deleteRow(j);
                }
            }
        }
    }
}

/*
* Создайте дополнительную HTML-страницу, содержащую текстовое поле ввода для отображения фамилии сотрудника
* и кнопку «Сохранить». Для главной страницы из предыдущего занятия создайте скрипт,
* который по нажатию кнопки (подтверждения ввода фамилии сотрудника) передает введенную фамилию в текстовое
* поле новой созданной страницы.
*/

function sendEname() {
    var textEName = document.getElementById('textEName').value;
    window.open('../html/EditEName.html?=' + textEName);
}