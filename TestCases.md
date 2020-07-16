---


---

<blockquote>
<p>Written with <a href="https://stackedit.io/">StackEdit</a>.</p>
</blockquote>
<h2 id="тест-кейс-№1-проверка-данных-в-поле-first-name">Тест-кейс №1 Проверка данных в поле “first name”</h2>
<h3 id="предусловия">Предусловия:</h3>
<ol>
<li>Зайти на сайт “ссылка на сайт”.<br>
<em>1. Открылась главная страница сайта, на которой есть кнопка “sign up”. </em></li>
<li>Нажать на кнопку “sign up”.<br>
<em>2. Открылось окно “sign up” с полями для ввода данных.</em></li>
</ol>
<h3 id="шаги">Шаги</h3>

<table>
<thead>
<tr>
<th>Действие</th>
<th>Ожидаемый результат</th>
</tr>
</thead>
<tbody>
<tr>
<td>Ввести в поле “first name” строку “Testname”</td>
<td>Допустимое значение, ошибка не появляется</td>
</tr>
<tr>
<td>Ввести в поле “first name” строку “Тестовоеимя”</td>
<td>Допустимое значение, ошибка не появляется</td>
</tr>
<tr>
<td>Оставить поле “first name” пустым</td>
<td>В строке под полем появляется ошибка: «Please enter a valid first name.», заполнение остальных полей возможно, регистрация пользователя невозможна.</td>
</tr>
<tr>
<td>Ввести в поле “first name” строку “abcdefghijklmnopqrstuvwxyzabcdefghijklmno”</td>
<td>В строке под полем появляется ошибка: «First name cannot exceed 40 characters.», заполнение остальных полей возможно, регистрация пользователя невозможна.</td>
</tr>
<tr>
<td>Ввести в поле “first name” строку &amp;*%#(^$@*&amp;!"№;:?(){}[]’/|\`~-_</td>
<td>В строке под полем появляется ошибка: «Please enter a valid first name.», заполнение остальных полей возможно, регистрация пользователя невозможна.</td>
</tr>
<tr>
<td>Ввести 0123456789</td>
<td>В строке под полем появляется ошибка: «Please enter a valid first name.», заполнение остальных полей возможно, регистрация пользователя невозможна.</td>
</tr>
<tr>
<td></td>
<td></td>
</tr>
</tbody>
</table><h2 id="тест-кейс-№2-проверка-данных-в-поле-last-name">Тест-кейс №2 Проверка данных в поле “last name”</h2>
<h3 id="предусловия-1">Предусловия:</h3>
<ol>
<li>Зайти на сайт “ссылка на сайт”.<br>
<em>1. Открылась главная страница сайта, на которой есть кнопка “sign up”. </em></li>
<li>Нажать на кнопку “sign up”.<br>
<em>2. Открылось окно “sign up” с полями для ввода данных.</em></li>
</ol>
<h3 id="шаги-1">Шаги</h3>

<table>
<thead>
<tr>
<th>Действие</th>
<th>Ожидаемый результат</th>
</tr>
</thead>
<tbody>
<tr>
<td>Оставить поле “last name” пустым</td>
<td>Допустимое значение, ошибка не появляется</td>
</tr>
<tr>
<td>Ввести в поле “last name” строку “Testname”</td>
<td>допустимое значение, ошибка не появляется</td>
</tr>
<tr>
<td>Ввести в поле “last name” строку “Тестовоеимя”</td>
<td>Допустимое значение, ошибка не появляется</td>
</tr>
<tr>
<td>Ввести в поле “last name” строку “abcdefghijklmnopqrstuvwxyzabcdefghijklmno”</td>
<td>В строке под полем появляется ошибка: «Last name cannot exceed 40 characters.», заполнение остальных полей возможно, регистрация пользователя невозможна.</td>
</tr>
<tr>
<td>Ввести в поле “last name” строку &amp;*%#(^$@*&amp;!"№;:?(){}[]’/|\`~-_</td>
<td>В строке под полем появляется ошибка: «Please enter a valid last name.», заполнение остальных полей возможно, регистрация пользователя невозможна.</td>
</tr>
<tr>
<td>Ввести в поле “last name” строку 0123456789</td>
<td>В строке под полем появляется ошибка: «Please enter a valid last name.», заполнение остальных полей возможно, регистрация пользователя невозможна.</td>
</tr>
<tr>
<td></td>
<td></td>
</tr>
</tbody>
</table><h2 id="тест-кейс-№3-проверка-данных-в-поле-nickname">Тест-кейс №3 Проверка данных в поле “nickname”</h2>
<h3 id="предусловия-2">Предусловия:</h3>
<ol>
<li>Зайти на сайт “ссылка на сайт”.<br>
<em>1. Открылась главная страница сайта, на которой есть кнопка “sign up”. </em></li>
<li>Нажать на кнопку “sign up”.<br>
<em>2. Открылось окно “sign up” с полями для ввода данных.</em></li>
</ol>
<h3 id="шаги-2">Шаги</h3>

<table>
<thead>
<tr>
<th>Действие</th>
<th>Ожидаемый результат</th>
</tr>
</thead>
<tbody>
<tr>
<td>Ввести в поле “nickname” строку “test”</td>
<td>Допустимое значение, ошибка не появляется</td>
</tr>
<tr>
<td>Ввести в поле “nickname” строку “тест”</td>
<td>Допустимое значение, ошибка не появляется</td>
</tr>
<tr>
<td>Оставить поле “nickname” пустым</td>
<td>В строке под полем появляется ошибка: «Please enter a valid nickname.», заполнение остальных полей возможно, регистрация пользователя невозможна.</td>
</tr>
<tr>
<td>Ввести в поле “nickname” строку “abcdefghijklmnopqrstuvwxyzabcdefghijklmno” в поле “nickname”</td>
<td>В строке под полем появляется ошибка: «Nickname cannot exceed 40 characters.», заполнение остальных полей возможно, регистрация пользователя невозможна.</td>
</tr>
<tr>
<td>Ввести в поле “nickname” строку &amp;*%#(^$@*&amp;!"№;:?(){}[]’/|\`~-_</td>
<td>В строке под полем появляется ошибка: «Please enter a valid nickname.», заполнение остальных полей возможно, регистрация пользователя невозможна.</td>
</tr>
<tr>
<td></td>
<td></td>
</tr>
</tbody>
</table><h2 id="тест-кейс-№4-проверка-данных-в-поле-password">Тест-кейс №4 Проверка данных в поле “password”</h2>
<h3 id="предусловия-3">Предусловия:</h3>
<ol>
<li>Зайти на сайт “ссылка на сайт”.<br>
<em>1. Открылась главная страница сайта, на которой есть кнопка “sign up”. </em></li>
<li>Нажать на кнопку “sign up”.<br>
<em>2. Открылось окно “sign up” с полями для ввода данных.</em></li>
</ol>
<h3 id="шаги-3">Шаги</h3>

<table>
<thead>
<tr>
<th>Действие</th>
<th>Ожидаемый результат</th>
</tr>
</thead>
<tbody>
<tr>
<td>Ввести в поле “password” строку dtddGBE!6&lt;k+G+P</td>
<td>В строке под полем “password” ошибка не появляется. Напротив каждого из условий создания пароля в строке под полем “password” появляется маркер ✔: <ul><li>Minimum of 8 characters</li></ul> <ul><li>1 uppercase letter</li></ul> <ul><li>1 lowercase letter</li></ul> <ul><li>1 number</li></ul></td>
</tr>
<tr>
<td>Оставить поле “password” пустым</td>
<td>В строке под полем появляется ошибка: «Password does not meet minimum requirements.» и подсказка в виде условий без маркера ✔, которые должны быть соблюдены при создании пароля: <ul><li>Minimum of 8 characters</li></ul> <ul><li>1 uppercase letter</li></ul> <ul><li>1 lowercase letter</li></ul> <ul><li>1 number</li></ul> заполнение остальных полей возможно, регистрация пользователя невозможна.</td>
</tr>
<tr>
<td>Ввести в поле “password” строку 0123456789</td>
<td>В строке под полем появляется ошибка: «Password does not meet minimum requirements.» и подсказка в виде условий без маркера ✔, которые должны быть соблюдены при создании пароля: <ul><li>1 uppercase letter</li></ul> <ul><li>1 lowercase letter</li></ul> заполнение остальных полей возможно, регистрация пользователя невозможна.</td>
</tr>
<tr>
<td>Ввести в поле “password” строку dtddGBE!6&lt;k+G+P, где буква Е написана кириллицей</td>
<td>В строке под полем “password” ошибка не появляется. Напротив каждого из условий создания пароля в строке под полем “password” появляется маркер ✔: <ul><li>Minimum of 8 characters</li></ul> <ul><li>1 uppercase letter</li></ul> <ul><li>1 lowercase letter</li></ul> <ul><li>1 number</li></ul></td>
</tr>
<tr>
<td>Ввести в поле “password” строку “абв123”</td>
<td>В строке под полем появляется ошибка: «Password does not meet minimum requirements.» и подсказка в виде условий без маркера ✔, которые должны быть соблюдены при создании пароля: <ul><li>Minimum of 8 characters</li></ul> <ul><li> 1 uppercase letter</li></ul> <ul><li>1 lowercase letter</li></ul> заполнение остальных полей возможно, регистрация пользователя невозможна.</td>
</tr>
<tr>
<td>Ввести в поле “password” строку &amp;*%#(^$@*&amp;!"№;:?(){}[]’/|\`~-_</td>
<td>В строке под полем появляется ошибка: «Password does not meet minimum requirements.» и подсказка в виде условий без маркера ✔, которые должны быть соблюдены при создании пароля: <ul><li> 1 uppercase letter</li></ul> <ul><li>1 lowercase letter</li></ul> <ul><li>1 number</li></ul></td>
</tr>
<tr>
<td>Ввести в поле “password” строку "a B 1 * ", где в качестве последнего (восьмого) символа используется пробел</td>
<td>В строке под полем появляется ошибка: «Password does not meet minimum requirements.» и подсказка в виде условий без маркера ✔, которые должны быть соблюдены при создании пароля: <ul><li>Minimum of 8 characters</li></ul></td>
</tr>
<tr>
<td>Ввести в поле “password” строку "    a B 1", где в качестве первых четырёх символов используется пробел</td>
<td>В строке под полем появляется ошибка: «Password does not meet minimum requirements.» и подсказка в виде условий без маркера ✔, которые должны быть соблюдены при создании пароля: <ul><li>Minimum of 8 characters</li></ul></td>
</tr>
</tbody>
</table><h2 id="тест-кейс-№5-проверка-данных-в-поле-password-confirmation">Тест-кейс №5 Проверка данных в поле “password confirmation”</h2>
<h3 id="предусловия-4">Предусловия:</h3>
<ol>
<li>Зайти на сайт “ссылка на сайт”.<br>
<em>1. Открылась главная страница сайта, на которой есть кнопка “sign up”. </em></li>
<li>Нажать на кнопку “sign up”.<br>
<em>2. Открылось окно “sign up” с полями для ввода данных.</em></li>
<li>Ввести dtddGBE!6&lt;k+G+P в поле “password”.<br>
<em>3. </em> В строке под полем “password” ошибка не появляется. Напротив каждого из условий создания пароля в строке под полем “password” загорается маркер ✔.</li>
</ol>
<h3 id="шаги-4">Шаги</h3>

<table>
<thead>
<tr>
<th>Действие</th>
<th>Ожидаемый результат</th>
</tr>
</thead>
<tbody>
<tr>
<td>Ввести в поле “password confirmation” строку dtddGBE!6&lt;k+G+P</td>
<td>Справа от поля ввода появляется маркер ✔</td>
</tr>
<tr>
<td>Ввести в поле “password confirmation” строку dtddGBE!6&lt;k+G+P, где буква Е написана кириллицей</td>
<td>В строке под полем появляется ошибка “You entered a wrong password.”</td>
</tr>
<tr>
<td>Оставить поле “password confirmation” пустым</td>
<td>В строке под полем появляется ошибка: “Confirm you password.”</td>
</tr>
<tr>
<td>Ввести в поле “password confirmation” строку 0123456789</td>
<td>В строке под полем появляется ошибка “You entered a wrong password.”</td>
</tr>
<tr>
<td></td>
<td></td>
</tr>
</tbody>
</table><h2 id="тест-кейс-№6-проверка-отображения-данных-при-вводе-в-поле--password">Тест-кейс №6 Проверка отображения данных при вводе в поле  “password”</h2>
<h3 id="предусловия-5">Предусловия:</h3>
<ol>
<li>Зайти на сайт “ссылка на сайт”.<br>
<em>1. Открылась главная страница сайта, на которой есть кнопка “sign up”. </em></li>
<li>Нажать на кнопку “sign up”.<br>
<em>2. Открылось окно “sign up” с полями для ввода данных.</em></li>
</ol>
<h3 id="шаги-5">Шаги</h3>

<table>
<thead>
<tr>
<th>Действие</th>
<th>Ожидаемый результат</th>
</tr>
</thead>
<tbody>
<tr>
<td>Ввести в поле “password” строку dtddGBE!6&lt;k+G+P</td>
<td>Пароль отображается в виде ●●●●●●●●●●●●●●●</td>
</tr>
<tr>
<td>Ввести в поле “password confirmation” один любой символ</td>
<td>Пароль отображается в виде ●</td>
</tr>
<tr>
<td></td>
<td></td>
</tr>
</tbody>
</table><h2 id="тест-кейс-№7-проверка-отображения-данных-при-вводе-в-поле--password-confirmation">Тест-кейс №7 Проверка отображения данных при вводе в поле  “password confirmation”</h2>
<h3 id="предусловия-6">Предусловия:</h3>
<ol>
<li>Зайти на сайт “ссылка на сайт”.<br>
<em>1. Открылась главная страница сайта, на которой есть кнопка “sign up”. </em></li>
<li>Нажать на кнопку “sign up”.<br>
<em>2. Открылось окно “sign up” с полями для ввода данных.</em></li>
<li>Ввести dtddGBE!6&lt;k+G+P в поле “password”.<br>
<em>3. </em> В строке под полем “password” ошибка не появляется. Напротив каждого из условий создания пароля в строке под полем “password” загорается маркер ✔.</li>
</ol>
<h3 id="шаги-6">Шаги</h3>

<table>
<thead>
<tr>
<th>Действие</th>
<th>Ожидаемый результат</th>
</tr>
</thead>
<tbody>
<tr>
<td>Ввести в поле “password confirmation” строку dtddGBE!6&lt;k+G+P</td>
<td>Пароль отображается в виде ●●●●●●●●●●●●●●●</td>
</tr>
<tr>
<td>Ввести в поле “password confirmation” один любой символ</td>
<td>Пароль отображается в виде ●</td>
</tr>
<tr>
<td></td>
<td></td>
</tr>
</tbody>
</table><h2 id="тест-кейс-№8-проверка-работоспособности-поля--напротив-строки-i-agree-with-the-terms-of-usage">Тест-кейс №8 Проверка работоспособности поля  напротив строки “I agree with the terms of usage”</h2>
<h3 id="предусловия-7">Предусловия:</h3>
<ol>
<li>Зайти на сайт “ссылка на сайт”.<br>
<em>1. Открылась главная страница сайта, на которой есть кнопка “sign up”. </em></li>
<li>Нажать на кнопку “sign up”.<br>
<em>2. Открылось окно “sign up” с полями для ввода данных.</em></li>
<li>Ввести в поле “first name” строку “Testname”.<br>
<em>3. В поле отображается “Testname”. В строке под полем ошибки не возникает.</em></li>
<li>Ввести в поле “last name” строку “Testname”.<br>
<em>4. В поле отображается “Testname”. В строке под полем ошибки не возникает.</em></li>
<li>Ввести в поле “nickname” строку “test”.<br>
<em>5. В поле отображается “test”. В строке под полем ошибки не возникает.</em></li>
<li>Ввести в поле “password” строку dtddGBE!6&lt;k+G+P<br>
<em>6. В поле отображается “●●●●●●●●●●●●●●●”. В строке под полем ошибки не возникает. Напротив каждого из условий создания пароля в строке под полем “password” загорается маркер ✔.</em></li>
<li>Ввести в поле “password confirmation” строку dtddGBE!6&lt;k+G+P<br>
<em>7. В поле отображается “●●●●●●●●●●●●●●●”. В строке под полем ошибки не возникает. </em></li>
<li>Перейти по ссылке “the terms of usage” в строке “I agree with the terms of usage”.<br>
<em>8. Открывается веб-страница с заголовком “TERMS OF USE” и информацией.</em><br>
<em>9. Поле возле строки “I agree with the terms of usage” становится активным.</em></li>
</ol>
<h3 id="шаги-7">Шаги</h3>

<table>
<thead>
<tr>
<th>Действие</th>
<th>Ожидаемый результат</th>
</tr>
</thead>
<tbody>
<tr>
<td>Кликнуть на поле возле строки “I agree with the terms of usage”</td>
<td>В поле отобразится маркер ✔.</td>
</tr>
<tr>
<td>Кликнуть за пределами поля, расположенного возле строки “I agree with the terms of usage”</td>
<td>Никакие изменения на веб-странице не происходят.</td>
</tr>
<tr>
<td></td>
<td></td>
</tr>
</tbody>
</table><h2 id="тест-кейс-№9-проверка--перехода-по-ссылке-в-строке--i-agree-with-the-terms-of-usage">Тест-кейс №9 Проверка  перехода по ссылке в строке  “I agree with the terms of usage”</h2>
<h3 id="предусловия-8">Предусловия:</h3>
<ol>
<li>Зайти на сайт “ссылка на сайт”.<br>
<em>1. Открылась главная страница сайта, на которой есть кнопка “sign up”. </em></li>
<li>Нажать на кнопку “sign up”.<br>
<em>2. Открылось окно “sign up” с полями для ввода данных.</em></li>
</ol>
<h3 id="шаги-8">Шаги</h3>

<table>
<thead>
<tr>
<th>Действие</th>
<th>Ожидаемый результат</th>
</tr>
</thead>
<tbody>
<tr>
<td>Перейти по ссылке “the terms of usage” в строке “I agree with the terms of usage”.</td>
<td>Открывается веб-страница с заголовком “TERMS OF USE” и информацией.</td>
</tr>
<tr>
<td></td>
<td></td>
</tr>
</tbody>
</table><h2 id="тест-кейс-№10-проверка--работоспособности-кнопки-submit">Тест-кейс №10 Проверка  работоспособности кнопки “SUBMIT”</h2>
<h3 id="предусловия-9">Предусловия:</h3>
<ol>
<li>Зайти на сайт “ссылка на сайт”.<br>
<em>1. Открылась главная страница сайта, на которой есть кнопка “sign up”. </em></li>
<li>Нажать на кнопку “sign up”.<br>
<em>2. Открылось окно “sign up” с полями для ввода данных.</em></li>
<li>Ввести в поле “first name” строку “Testname”.<br>
<em>3. В поле отображается “Testname”. В строке под полем ошибки не возникает.</em></li>
<li>Ввести в поле “last name” строку “Testname”.<br>
<em>4. В поле отображается “Testname”. В строке под полем ошибки не возникает.</em></li>
<li>Ввести в поле “nickname” строку “test”.<br>
<em>5. В поле отображается “test”. В строке под полем ошибки не возникает.</em></li>
<li>Ввести в поле “password” строку dtddGBE!6&lt;k+G+P<br>
<em>6. В поле отображается “●●●●●●●●●●●●●●●”. В строке под полем ошибки не возникает. Напротив каждого из условий создания пароля в строке под полем “password” загорается маркер ✔.</em></li>
<li>Ввести в поле “password confirmation” строку dtddGBE!6&lt;k+G+P<br>
<em>7. В поле отображается “●●●●●●●●●●●●●●●”. В строке под полем ошибки не возникает. </em></li>
<li>Перейти по ссылке “the terms of usage” в строке “I agree with the terms of usage”.<br>
<em>8. Открывается веб-страница с заголовком “TERMS OF USE” и информацией.</em></li>
<li>Кликнуть на поле возле строки “I agree with the terms of usage”<br>
<em>9. В поле отобразится маркер ✔.</em></li>
</ol>
<h3 id="шаги-9">Шаги</h3>

<table>
<thead>
<tr>
<th>Действие</th>
<th>Ожидаемый результат</th>
</tr>
</thead>
<tbody>
<tr>
<td>Кликнуть на кнопку “SUBMIT”</td>
<td>Появляется pop-up с сообщением “You have been successfully registered!”</td>
</tr>
<tr>
<td>Кликнуть за пределами кнопки “SUBMIT”</td>
<td>Никакие изменения на веб-странице не происходят.</td>
</tr>
<tr>
<td></td>
<td></td>
</tr>
</tbody>
</table>