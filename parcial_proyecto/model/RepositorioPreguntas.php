<?php

include_once "PDORepository.php";
include_once "Pregunta.php";

class RepositorioPreguntas {

    private static $instance;

    public static function getInstance() {

        if (!isset(self::$instance)) {
            self::$instance = new self();
        }

        return self::$instance;
    }

    private function __construct() {
        
    }

    public function registrarPregunta($titulo, $texto, $idUser){

        date_default_timezone_set('America/Argentina/Buenos_Aires');

        $pdo = new PDORepository();

        $sql = "INSERT INTO preguntas_usuario (titulo, pregunta, fecha_creacion, id_usuario) VALUES (:titulo, :pregunta, :fecha_creacion, :id_usuario);";

        $args = array(':titulo' => $titulo, 
        ':pregunta' => $texto, 
        ':fecha_creacion' => date("Y-m-d H:i:s"), 
        ':id_usuario' => $idUser);

        return $pdo->ejecutarConsulta($sql, $args);

    }

    public function obtenerListaDePreguntas() {

        $pdo = new PDORepository();

        $mapper = function($row) {
            $resource = new Pregunta($row['id'], $row['titulo'], $row['pregunta'], $row['fecha_creacion'], $row['usuario']);
            return $resource;
        };

        $answer = $pdo->queryList(
                "SELECT preguntas_usuario.id, titulo, pregunta, fecha_creacion, usuario FROM preguntas_usuario INNER JOIN usuario ON (usuario.id = preguntas_usuario.id_usuario) ORDER BY fecha_creacion;", $mapper);

        return $answer;
    }

    public function obtenerPregunta($idPregunta){
        $pdo = new PDORepository();
        
        $mapper = function($row) {
            $resource = new Pregunta($row['id'], $row['titulo'], $row['pregunta'], $row['fecha_creacion'], $row['id_usuario']);
            return $resource;
        };

        $sql = "SELECT * FROM preguntas_usuario WHERE id=:id;";

        $args = array(':id' => $idPregunta);

        $answer = $pdo->queryList($sql, $mapper, $args);

        return $answer[0];
    }

    public function eliminar($idPregunta){

        $pdo = new PDORepository();

        $sql = "DELETE FROM preguntas_usuario WHERE id=:id";
    
        $args = array(':id' => $idPregunta);

        return ( ($pdo->ejecutarConsulta($sql, $args)) == false );
    }

    public function editarPregunta($titulo, $texto, $idPregunta){

        $pdo = new PDORepository();

        $sql = "UPDATE preguntas_usuario SET titulo=:titulo, pregunta=:pregunta WHERE id=:id";

        $args = array(':titulo' => $titulo, ':pregunta' => $texto, ':id' => $idPregunta);

        return ( ($pdo->ejecutarConsulta($sql, $args)) == false );

    }

}