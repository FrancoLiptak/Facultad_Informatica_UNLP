<?php


/**
 * Description of TwigView
 *
 * @author fede
 */
require_once './vendor/autoload.php';

abstract class TwigView {

    private static $twig;

    public static function getTwig() {

        if (!isset(self::$twig)) {

            Twig_Autoloader::register();
            $loader = new Twig_Loader_Filesystem('./templates');
            self::$twig = new Twig_Environment($loader);
        }
        return self::$twig;
    }

}
