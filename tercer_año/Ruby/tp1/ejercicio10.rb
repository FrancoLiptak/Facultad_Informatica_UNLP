def a_ul unHash
    string = "<ul>"
    unHash.collect {|key, value| string << "<li>#{key}: #{value}</li>"}
    string << "</ul>"
end

p a_ul({ perros: 1, gatos: 1, peces: 0})