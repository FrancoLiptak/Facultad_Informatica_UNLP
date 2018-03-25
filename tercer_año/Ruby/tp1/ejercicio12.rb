class String
    def rot(n=1)
      n=1 unless n.kind_of? Integer
      n.times do
        char = self.shift
        self.push(char)
      end
      self
    end
    def push(other)
      newself = self + other.to_s.dup.shift.to_s
      self.replace(newself)
    end
    def shift
      return nil if self.empty?
      item=self[0]
      self.sub!(/^./,"")
      return nil if item.nil?
      item.chr
    end
  end

a = "¡Bienvenidos a la cursada 2015 de TTPS Opción Ruby!"

p a.rot 13