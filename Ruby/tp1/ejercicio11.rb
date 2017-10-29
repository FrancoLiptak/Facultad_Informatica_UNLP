def rot13 value 
    value.tr("a-zA-Z", "n-za-mN-ZA-M")
end

p rot13("¡Bienvenidos a la cursada 2015 de TTPS Opción Ruby!")