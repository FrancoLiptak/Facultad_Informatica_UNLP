<?php

class Pregunta{
    private $titulo;
    private $texto;
    private $nombreUsuario;
    private $fecha;
    private $idPregunta;

    function __construct($idPregunta, $titulo, $texto, $fecha, $nombreUsuario) {

        $this->idPregunta = $idPregunta;
        $this->titulo = $titulo;
        $this->texto = $texto;
        $this->fecha = $fecha;
        $this->nombreUsuario = $nombreUsuario;
    }

    public function idPregunta(){
        return $this->idPregunta;
    }

    public function titulo(){
        return $this->titulo;
    }

    public function texto(){
        return $this->texto;
    }

    public function nombreUsuario(){
        return $this->nombreUsuario;
    }

    public function fecha(){
        return $this->fecha;
    }
    
}