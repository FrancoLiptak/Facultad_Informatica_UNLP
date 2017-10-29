def fibonacci
    Enumerator.new do |y|
        a = b = 1

        loop do
        y << a # << añade al final del Enumerator.
        a, b = b, a + b
        end
    end
end