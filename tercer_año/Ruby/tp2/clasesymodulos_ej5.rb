class GenericFactory
    def self.create (**args)
        new(**args)
    end
    def initialize (**args)
        raise NotImplementedError
    end
end

class Prueba < GenericFactory

    def initialize  (**args) 
    end

end