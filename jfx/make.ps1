function compilation{
    echo "........compiling..........."
    javac --module-path javafx/sdk/lib --add-modules javafx.media,javafx.fxml,javafx.swing,javafx.web .\*.java
    echo "...........done............."
}

function Run{
    $param2= Read-Host "Press Enter to Compile"
    compilation;
}

$params =$args[0]
switch ($params){
    "" {Run; Break}
}