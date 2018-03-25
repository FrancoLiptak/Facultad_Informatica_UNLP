module Reverso
    
    def di_tcejbo(anObject)
        p anObject.object_id.to_s.reverse
    end

    def ssalc
        p self.class.to_s.reverse
    end
end
    
class Prueba
    include Reverso
end
    
    
Prueba.new.ssalc
Prueba.new.di_tcejbo 'hola'