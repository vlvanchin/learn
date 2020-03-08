word = "python"
list = []
for ch in word:
	list.append(ch)
print list

string = ""
for i in range(len(list)):
	string += list[i]
	
print string


# example on dict
dict = {}
for i,ch in enumerate(string):
	dict[i] = ch
print dict

for k in dict:
	print k , "=" , dict[k]