<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of PDORepository
 *
 * @author fede
 */
class PDORepository {
    
    const USERNAME = "root";
    const PASSWORD = "pass";
	const HOST ="db";
	const DB = "base";
    
    
    private function getConnection(){
        $u=self::USERNAME;
        $p=self::PASSWORD;
        $db=self::DB;
        $host=self::HOST;
        $connection = new PDO("mysql:dbname=$db;host=$host", $u, $p);
        return $connection;
    }
    
    public function queryList($sql, $mapper, $args = null){
        $connection = $this->getConnection();
        $stmt = $connection->prepare($sql);

        if($args == null){
            $stmt->execute();
        }else{
            $stmt->execute($args);
        }

        $list = [];
        while($element = $stmt->fetch()){
            $list[] = $mapper($element);
        }
        return $list;
    }
    
    public function ejecutarConsulta($sql, $args){
        $connection = $this->getConnection();
        $stmt = $connection->prepare($sql);
        $stmt->execute($args);
        $element = $stmt->fetch();

        return $element;
    }

}
