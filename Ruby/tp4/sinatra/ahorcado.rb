class Ahorcado

    def initialize word
        @word = word.downcase.chars.uniq
        @state = { 'guessed_letters' => 0, 'remaining_attempts' => 3 }
    end

    def state
        if @state['guessed_letters'] == @word.length
            return "Juego terminado, ganaste!"
        elsif @state['remaining_attempts'] == 0
            return "Juego terminado, perdiste!"
        else
            return "La cantidad de letras adivinadas es: #{@state['guessed_letters']}. 
                    La cantidad de intentos fallidos es: #{@state['failed_attempts'] - 1}.
                    La cantidad de intentos posibles es: #{@state['remaining_attempts']}"
        end
    end

    def canContinue?
        ( @state['guessed_letters'] != @word.length ) && ( @state['remaining_attempts'] != 0 )
    end

    def tryWith letter
        if @word.include? letter.downcase
            @state['guessed_letters'] = @state['guessed_letters'] + 1
        else
            @state['remaining_attempts'] = @state['remaining_attempts'] - 1
        end
    end
end