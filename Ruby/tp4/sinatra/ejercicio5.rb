require 'bundler'
require 'sinatra/advanced_routes'
Bundler.require

get '/' do
    Sinatra::Application.each_route do |route|
        puts route.verb + " " + route.path
      end
end

get '/mcm/:a/:b' do
    puts (params['a'].to_i).lcm(params['b'].to_i)
end

get '/mcd/:a/:b' do
    puts (params['a'].to_i).gcd(params['b'].to_i)
end 

get '/sum/*' do
    puts params['splat'].to_a.inject(0){|sum,x| sum + x }
end

get '/even/*' do
    puts params['splat'].to_a
end