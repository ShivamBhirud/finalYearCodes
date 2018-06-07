from flask import Flask, request, render_template

app = Flask(__name__)

@app.route("/")
def index():
    return render_template("index.html")

@app.route("/attendance.html")
def att():
    return render_template("attendance.html")

@app.route("/show.html", methods = ['GET', 'POST'])
def show():
    name = request.form.get('name')
    in_time = request.form.get("it")
    out_time = request.form.get("ot")
    return render_template("show.html", name = name,inTime = in_time, outTime = out_time)

@app.route('/notice.html')
def Notice():
    notice = "Meeting is finalized today at 9:00 am."
    return render_template('notice.html', data = notice)

@app.route('/salary.html')
def salary():
    return render_template('salary.html')

@app.route('/sal.html', methods = ['GET', 'POST'])
def salShow():
    days = int(request.form.get("days"))
    sal = int(request.form.get("sal"))
    res = days*sal
    print res
    return render_template('sal.html', data = res)

if __name__ == ("__main__"):
    app.run("localhost", debug=True)