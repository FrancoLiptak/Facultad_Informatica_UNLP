<?php

class Usuario{
    private $id;
    private $usuario;
    private $nombre;
    private $apellido;
    private $mail;
    private $pais;
    private $fecha_registracion;

    public function id(){
        return $this->id;
    }

    public function usuario(){
        return $this->usuario;
    }

    public function nombre(){
        return $this->nombre;
    }

    public function apellido(){
        return $this->apellido;
    }

    public function mail(){
        return $this->mail;
    }

    public function pais(){
        return $this->pais;
    }

    public function fecha_registracion(){
        return $this->fecha_registracion;
    }

    function __construct($id, $usuario, $nombre, $apellido, $mail, $pais, $fecha_registracion){
        $this->id = $id;
        $this->usuario = $usuario;
        $this->nombre = $nombre;
        $this->apellido = $apellido;
        $this->mail = $mail;
        $this->pais = $pais;
        $this->fecha_registracion = $fecha_registracion;
    }
}