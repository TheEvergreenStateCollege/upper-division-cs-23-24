from flask_sqlalchemy import SQLAlchemy

# Initiates and instance of SQLAlchemy
database = SQLAlchemy()

# Define models
class User(database.Model):
    __tablename__ = 'User'
    id = database.Column(database.Integer, primary_key=True)
    first_name = database.Column(database.String(100), nullable=False)
    last_name = database.Column(database.String(100), nullable=False)
    email = database.Column(database.String(100), nullable=False)
    contact = database.Column(database.String(20), nullable=False)
    address1 = database.Column(database.String(255), nullable=False)
    address2 = database.Column(database.String(255), nullable=False)

class Trip(database.Model):
    __tablename__ = 'Trip'
    id = database.Column(database.Integer, primary_key=True)
    time = database.Column(database.Integer) # really a string as the time is not a number
    date = database.Column(database.Date)
    elapsed = database.Column(database.String)
    orig = database.Column(database.String)
    dest = database.Column(database.String)
    miles = database.Column(database.String) # need to stirp this of text

# class UpdateTrip(database.Model):
#     __tablename__ = 'UpdateTrip'
#     user = database.Column(database.String)
#     time = database.Column(database.Integer)
#     date = database.Column(database.Date)
#     elapsed = database.Column(database.String)
#     orig = database.Column(database.String)
#     dest = database.Column(database.String)
#     miles = database.Column(database.String) 