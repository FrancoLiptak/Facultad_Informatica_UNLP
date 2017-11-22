require 'bundler'
require_relative 'ahorcado'
require 'sinatra'

words = [Ahorcado.new('cristiano'), Ahorcado.new('riquelme'), Ahorcado.new('messi'), Ahorcado.new('maradona')]

post '/' do
    redirect "/#{rand(words.size)}"
end

get '/:id' do
    words[params['id'].to_i].state
end

put '/:id/:intento' do
    if  words[params['id'].to_i].canContinue?
        words[params['id']].tryWith(params['intento'])
        redirect "/#{params['id']}"
    else
        "El juego terminó"
    end
end 