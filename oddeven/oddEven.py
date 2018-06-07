from flask import Flask,request, render_template

app = Flask(__name__)

@app.route('/')
def index():
	return render_template('index.html')

@app.route('/num.html',methods=['POST','GET'])
def drawUI():
    return render_template('num.html',count=int(request.form['itm']))

@app.route('/sort.html',methods=['POST','GET'])
def sortdata():
	a = request.form.getlist('numb')
	oddEvenSort(a)
	return render_template('sort.html',data=a)  	

def oddEvenSort(a):
	b = a	
	while(a == b.sort()):
		for i in range(0, len(a)-1):
			if(a[i]>a[i+1]):
				a[i],a[i+1] = a[i+1],a[i]
			i = i+2
		for i in range(1,len(a)-1):
			if(a[i]>a[i+1]):
				a[i],a[i+1] = a[i+1],a[i]
			i = i+2
	return a

if __name__ == '__main__':
	app.run("localhost", debug=True)
