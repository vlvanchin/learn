word = "pithon phrasebook"

string = ""
for ch in word:
	if ch == 'i':
		string += 'y'
		continue
	if ch == ' ':
		break
	string += ch
print string

count = input('enter a number');

for i in range(count) :
  for j in range(i) :
    print '* '

