require 'bundler'
require 'sinatra/advanced_routes'
Bundler.require

get '/' do
    s = "Las rutas disponibles son: <br>"
    Sinatra::Application.each_route do |route|
        s << route.verb + " " + route.path + "<br>"
    end
    s
end

get '/mcm/:a/:b' do
    a = params['a'].to_i
    b= params['b'].to_i
    a.lcm(b).to_s
end

get '/mcd/:a/:b' do
    a = params['a'].to_i
    b= params['b'].to_i
    a.gcd(b).to_s
end 

get '/sum/*' do | path |
    arrayOfIntegers = path.split('/').map(&:to_i)

    ( arrayOfIntegers.inject(0){|sum,x| sum + x } ).to_s
end

get '/even/*' do | path |
    arrayOfIntegers = path.split('/').map(&:to_i)

    ( arrayOfIntegers.select(&:even?).size ).to_s
end

post '/random' do
    rand.to_s
end

post '/random/:lower/:upper' do |lower, upper|
    rand(lower..upper).to_s
end