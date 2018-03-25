require 'matrix'

class Image                                                                                    
    attr_accessor :data, :size                                                                     

    def initialize(data = nil, size = 2)                                                           
        self.size = size                                                                           
        self.data = data || Matrix.build(size) { Math::PI }                                        
    end                                                                                            

    def header_bytes                                                                               
        Matrix.rows([data.first(size)])                                                            
    end                                                                                            
                                                                                               
    def filter_a                                                                                   
        Image.new data.lazy.map { | e| e ** 1.2 }                                                  
    end                
    
    def filter_b                                                                                   
        Image.new data.lazy.map { | e| e ** 1.4 }                                                  
    end
    
    def filter_c                                                                                   
        Image.new data.lazy.map { | e| e ** 1.8 }                                                  
    end
    
    def filter_d                                                                                   
        Image.new data.lazy.map { | e| e ** 2 }                                                    
    end 
    
    def filter_e                                                                                   
        Image.new data.lazy.map { | e| e ** 2.2 }                                                  
    end 
    
    def filter_f                                                                                   
        Image.new data.lazy.map { | e| e ** 2.4 }                                                  
    end                                                                                            

    def all_filters                                                                                
        ('a'..'f').inject(self) do | pipe, type|                                                   
            pipe.public_send "filter_#{type}"                                                      
        end                                                                                        
    end                                                                                            
end

image = Image.new
image.filter_a.filter_c.filter_e # => Esto no realiza ningún cálculo.
image.filter_a.filter_c.filter_e.header_bytes # => Esto sí realiza cálculos para obtener la info de la cabecera.

=begin

Explicación enumeradores lazy:

Existen enumeradores infinitos (por ejemplo Fibonacci). Siempre que le pida un next me va a devolver algún valor.
Existen métodos (como select, collect, etc) que no pueden trabajar con enumeradores infinitos.
La razón es que siempre va a haber datos nuevos y por ende, nunca terminaría.

Los enumeradores lazy (subclase de Enumerator) generalmente están asociados con una clausula next o first, que indica a cuantos elementos afecta.
Los enumeradores lazy no hacen mas de lo que se necesita.
A éstos, por ejemplo, puedo pedirle los "first 10". De esta forma, el enumerador lazy solo calculará los primeros 10 elementos (me aseguro de que va a terminar).

data es un enumerator. data.lazy me devuelve un enumerador lazy.
El segundo mensaje sí realiza cálculos, ya que en el método header_bytes indico (mediante el mensaje first), con cuantos elementos del enumerator quiero trabajar.


=end