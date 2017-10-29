module GenericFactory
    
    module ClassMethods
        def create **args
            new **args
        end
    end

    def initialize **args
        raise NotImplementedError
    end

    def self.included base
        base.extend ClassMethods
    end 

    # una función self.included se llama cuando un módulo es incluído en una clase. 
    # Esto permite que los métodos de ClassMethods sean incluídos en el contexto de la base.
    # Gracias al uso de extend, los métodos de ClassMethods serán métodos de clase.
end

class Prueba
    include GenericFactory 
    # Dado que lo lógico es que initialize sea método de instancia, uso 'include' para incluir los métodos de GenericFactory como métodos de instancia.
    
    def initialize **args
    end
end