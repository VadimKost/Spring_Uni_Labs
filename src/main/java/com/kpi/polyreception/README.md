# Звіт до лабораторної роботи №3

## Тема: *Використання шаблонізаторів  у веб-застосунках* <br>
## Завдання:
1. У відповідності до номеру вашого варіанту отримайте від викладача назву шаблонізатора, який ви будете використовувати (Thymeleaf або JSP).

2. Для проекту з попередньої лабораторної роботи реалізуйте рівень представлення. Проект має функціонувати як веб-застосунок (контент генерується на сервері і у вигляді HTML відправляється не клієнт).
   При цьому:
   <br> 2.1) щонайменше один раз має бути використане умовне форматування (елементи if, unless, Elvis-operator);
   <br> 2.2) щонайменше один раз має бути використаний елемент вибору (елементи switch/case, choose/when);
   <br> 2.3) щонайменше один раз має бути використаний цикл (елементи each, forEach);
   <br> 2.4) сайт має бути стійким до XSS-атак;
   <br> 2.5) функціональність пов’язану з безпекою (аутентифікація та авторизація) в цій роботі можна не реалізовувати (це тема окремої лабораторної роботи).


## Хід роботи:
1. Використовуємо шаблонізатор з попередньої ЛР (тобто, Thymeleaf). Maven-залежність вже присутня у проєкті. <br>
2. Реалізовуємо рівень представлення.
  <br> 2.1) Використовуємо умовне форматування: <br> ![IfExample](readme_img/1.jpg) <br> <br> ![UnlessExample](readme_img/2.jpg) <br>
  <br> 2.2) Використовуємо елементи вибору: <br> ![SwitchExample](readme_img/3.jpg) <br>
  <br> 2.3) Використовуємо цикл: <br> ![EachExample](readme_img/4.jpg) <br> 
  <br> 2.4) Використовуємо можливості Spring Security для запобігання XSS-атак: ![XssProtection](readme_img/5.jpg) <br>

## ВИСНОВКИ:
На цій лабораторній роботі ми навчились використовувати шаблонізатори у веб-застосунках. 
<br> А саме: використали шаблонізатор Thymeleaf та деякі з його можливостей, як-от: умовне форматування, елемент вибору, цикл. 
<br> Також зробили сайт стійким до XSS-атак використовуючи можливості Spring Security.