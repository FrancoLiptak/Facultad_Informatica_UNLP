def da_nil?
    yield.nil? ? true : false
end

p da_nil? { }
p da_nil? { "hola" }