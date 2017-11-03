<?php

include_once "PDORepository.php";
include_once "Usuario.php";

class UserRepository {

    private static $instance;

    public static function getInstance() {

        if (!isset(self::$instance)) {
            self::$instance = new self();
        }

        return self::$instance;
    }

    private function __construct() {
        
    }

    public function iniciarSesion($email, $pass){

        $mapper = function($row) {
            $resource = new Usuario($row['id'], $row['usuario'], $row['nombre'], $row['apellido'], $row['mail'], $row['pais'], $row['fecha_registracion']);
            return $resource;
        };

        $pdo = new PDORepository();

        $sql = "SELECT id, usuario, nombre, apellido, mail, pais, fecha_registracion FROM usuario WHERE mail=:mail AND clave=:clave";

        $args = array(':mail' => $email, ':clave' => $pass); 

        return $pdo->queryList($sql, $mapper, $args);

    }

}