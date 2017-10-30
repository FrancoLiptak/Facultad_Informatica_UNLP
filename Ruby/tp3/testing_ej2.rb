def incrementar x, delta = 1
    raise RuntimeError if x.class === "String"
    x + delta
end

def concatenar *parametros
	parametros.join(' ').tr("\t\n", '').squeeze(" ")
end
