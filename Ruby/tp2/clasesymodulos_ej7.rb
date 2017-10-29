module Opposite
    def opposite
        !self
    end
end

TrueClass.include Opposite
FalseClass.include Opposite

p "El opuesto de True es #{true.opposite}"
p "El opuesto de False es #{false.opposite}"