# Print File IDEA-Plugin

Плагин добавляет в меню Tools действие Print File  
В тулбаре внизу плагин добавляет окно для вывода  
Eсли оно не отображается, то включите его в View->Tool Windows->Print File Console  


По нажатию на Print File открывается окно с выбором файла  
Путь до выбранного файла передается параметром в Gradle Task([необходимый в проекте Gradle Plugin](https://github.com/stormkirill98/GradlePlugin))  
Файл выводится построчно в Print File Console  
Первой строкой выводится путь до файла  
Если строка начинается с #skip N, то будет пропущено N следующих строк  
