require 'minitest/autorun'
require 'minitest/spec'
require_relative 'testing_ejerciciosParaTestear'


describe '#contar' do
    describe 'cuando se le pasa un string' do
        it 'debe retornar 5' do
        assert_equal(5, contar("La casa de la esquina tiene la puerta roja y la ventana blanca.", "la"))
        end
    end


    describe 'cuando no se le pasa un string' do
        it 'arroja un NoMethodError' do
            x = 10
            assert_raises(NoMethodError) do
                contar(x, "la")
            end
        end
    end
end

describe '#contar_palabras' do
    describe 'cuando se le pasa un string' do
        it 'debe retornar 4' do
        assert_equal(4, contar_palabras("La casa de la esquina tiene la puerta roja y la ventana blanca.", "la"))
        end
    end


    describe 'cuando no se le pasa un string' do
        it 'arroja un NoMethodError' do
            x = 10
            assert_raises(NoMethodError) do
                contar_palabras(x, "la")
            end
        end
    end
end

describe '#longitud' do
    describe 'cuando se le pasa un arreglo' do
        it 'debe ser igual' do
        assert_equal([4, 6, 4, 12], longitud(['TTPS', 'Opción', 'Ruby', 'Cursada 2015']))
        end
    end


    describe 'cuando no se le pasa un arreglo' do
        it 'arroja un NoMethodError' do
            x = 10
            assert_raises(NoMethodError) do
                longitud(x)
            end
        end
    end
end

# Práctica 2

describe '#ordenar_arreglo' do
    describe 'cuando se le pasa un arreglo' do
        it 'debe ser igual' do
        assert_equal([0, 1, 2, 3, 4, 6, 10], ordenar_arreglo([1, 4, 6, 2, 3, 0, 10]))
        end
    end


    describe 'cuando no se le pasa un arreglo' do
        it 'arroja un NoMethodError' do
            x = 10
            assert_raises(NoMethodError) do
                ordenar_arreglo(x)
            end
        end
    end
end

describe '#ordenar' do
    describe 'cuando se le pasa un arreglo' do
        it 'debe ser igual' do
        assert_equal([0, 1, 2, 3, 4, 5, 6, 9, 10], ordenar(1, 4, 6, 2, 3, 5, 0, 10, 9))
        end
    end


    describe 'cuando no se le pasa un arreglo' do
        it 'devuelve un arreglo vacío' do
            assert_equal([], ordenar())
        end
    end
end

describe '#opposite' do
    describe 'chequeo el opuesto a false' do
        it 'debe ser igual' do
        FalseClass.include Opposite
        assert_equal(true, false.opposite)
        end
    end


    describe 'chequeo el opuesto a true' do
        it 'debe ser igual' do
        TrueClass.include Opposite
        assert_equal(false, true.opposite)
        end
    end
end