<?php

class Ruteador {

    private static $instance;

    public static function getInstance() {
        
        if (!isset(self::$instance)) {
            self::$instance = new self();
        }

        return self::$instance;
    }

    public function main(){

        if ( !isset($_GET['controlador'] )){
            $nombreControlador = "IndexController";
        }else{
            $nombreControlador = ucwords($_GET['controlador']) . "Controller";
        }

        if ( !isset($_GET['accion'] )){
            $nombreAccion = "show";
        }else{
            $nombreAccion = $_GET['accion'];
        }

        require_once "$nombreControlador.php";

        $controlador = $nombreControlador::getInstance();

        $controlador->$nombreAccion();
    }

}