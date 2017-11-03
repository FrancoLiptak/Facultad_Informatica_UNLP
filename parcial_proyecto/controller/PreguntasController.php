<?php

include_once "./view/TwigView.php";
include_once "SessionController.php";
include_once "./model/RepositorioPreguntas.php";

class PreguntasController{

    private static $instance;
    
    public static function getInstance() {
        
        if (!isset(self::$instance)) {
            self::$instance = new self();
        }

        return self::$instance;
    }

    public function mostrarCrearPregunta($mensaje = null) {
        $controladorSession = SessionController::getInstance();
        echo TwigView::getTwig()->render('preguntas.html.twig', array('usuario' => $controladorSession->obtenerUsuario(), 'logueado' => $controladorSession->isLogged(), 'mensaje' => $mensaje));

    }

    public function registraPregunta(){
        $controladorSession = new SessionController();
        $controladorSession->start_session();

        if ( $controladorSession->isLogged() ){
            
            $titulo = $_POST["titulo"];
            $texto = $_POST["texto"];
            $idUser = $_SESSION["user"]->id();


            if ( isset($titulo) && isset($texto) ){

                $repositorioPreguntas = RepositorioPreguntas::getInstance();
                $resultado = $repositorioPreguntas->registrarPregunta($titulo, $texto, $idUser);

                if ( $resultado == true ){
                    
                    $this->mostrarListaPreguntas();

                }else{
                    $this->mostrarCrearPregunta('Ha habido un error. Por favor, intente denuevo.');
                }
            }else{
                $this->mostrarCrearPregunta('Debe completar los campos. Por favor, intente denuevo.');
            }

        }else{
            $this->mostrarCrearPregunta('Usted no está loggueado.');
        }
  
    }

    public function mostrarListaPreguntas($mensaje = null){
        $repositorioPreguntas = RepositorioPreguntas::getInstance();
        $preguntas = $repositorioPreguntas->obtenerListaDePreguntas();

        $controladorSession = SessionController::getInstance();

        echo TwigView::getTwig()->render('listaPreguntas.html.twig', array('mensaje' => $mensaje, 'usuario' => $controladorSession->obtenerUsuario(), 'preguntas' => $preguntas, 'logueado' => $controladorSession->isLogged()));

    }

    public function eliminar(){
        $idPregunta = $_GET["id"];

        $repositorioPreguntas = RepositorioPreguntas::getInstance();

        $resultado = $repositorioPreguntas->eliminar($idPregunta);

        if ( $resultado == true){
            self::mostrarListaPreguntas("La pregunta se ha eliminado correctamente.");
        }else{
            self::mostrarListaPreguntas("La pregunta no se ha eliminado.");
        }

    }

    public function mostrarEditarPregunta($mensaje = null){

        $idPregunta = $_GET["id"];

        $repositorioPreguntas = RepositorioPreguntas::getInstance();
        $pregunta = $repositorioPreguntas->obtenerPregunta($idPregunta);

        $controladorSession = SessionController::getInstance();

        echo TwigView::getTwig()->render('editarPregunta.html.twig', array('mensaje' => $mensaje, 'usuario' => $controladorSession->obtenerUsuario(), 'pregunta' => $pregunta, 'logueado' => $controladorSession->isLogged()));

    }

    public function editarPregunta(){
        $controladorSession = new SessionController();
        $controladorSession->start_session();

        if ( $controladorSession->isLogged() ){
            
            $titulo = $_POST["titulo"];
            $texto = $_POST["texto"];
            $idPregunta = $_GET["id"];


            if ( isset($titulo) && isset($texto) && isset($idPregunta) ){

                $repositorioPreguntas = RepositorioPreguntas::getInstance();
                $resultado = $repositorioPreguntas->editarPregunta($titulo, $texto, $idPregunta);

                if ( $resultado == true ){
                    
                    $this->mostrarListaPreguntas("La pregunta se ha editado correctamente");

                }else{
                    $this->mostrarCrearPregunta('Ha habido un error. Por favor, intente denuevo.');
                }
            }else{
                $this->mostrarCrearPregunta('Debe completar los campos. Por favor, intente denuevo.');
            }

        }else{
            $this->mostrarCrearPregunta('Usted no está loggueado.');
        }
  
    }
}