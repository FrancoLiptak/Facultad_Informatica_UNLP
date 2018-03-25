require 'sinatra'

set(:number) { | value | condition { $value = Random::rand(42) } }

get '/', :number => 42 do
    [200, [$value.to_s]]
end
  
get '/' do
    [404, [$value.to_s]]
end