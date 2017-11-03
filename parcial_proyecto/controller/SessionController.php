<?php

include_once "./view/TwigView.php";
include_once "IndexController.php";
include_once "PreguntasController.php";
include_once "./model/UserRepository.php";

class SessionController{

    private static $instance;
    
    public static function getInstance() {
        
        if (!isset(self::$instance)) {
            self::$instance = new self();
        }

        return self::$instance;
    }

    public function login() {

        if ( !self::isLogged() ){

            $email = $_POST["email"];
            $pass = $_POST["pass"];

            if ( isset($email) && isset($pass) ){

                $repositorioDeUsuario = UserRepository::getInstance();
                $resultado = $repositorioDeUsuario->iniciarSesion($email, $pass);

                if ( isset($resultado) && !($resultado == NULL)){

                    $_SESSION["user"] = $resultado[0];
                    $controladorPreguntas = PreguntasController::getInstance();
                    $controladorPreguntas->mostrarCrearPregunta();

                }else{

                    $controladorIndex = IndexController::getInstance();
                    $controladorIndex->show('Ha habido un error. Por favor, intente denuevo.');

                }
            }else{

                $controladorIndex = IndexController::getInstance();
                $controladorIndex->show('Debe completar los campos. Por favor, intente denuevo.');

            }

        }else{

            $controladorIndex = IndexController::getInstance();
            $controladorIndex->show('Usted ya está loggeado.');

        }

    }

    public function isLogged(){
        self::start_session();

        return isset($_SESSION["user"]);
    }

    public function start_session(){
        session_start();
    }

    public function close_session(){
        self::start_session();
        session_destroy();

        $controladorIndex = IndexController::getInstance();
        $controladorIndex->show();
    }

    public function obtenerUsuario(){
        self::start_session();

        return $_SESSION["user"];
    }
}