import pytest
from app import create_app,db,task

@pytest.fixture
def client():
    app = create_app()
    app.config.update({
        "SQLALCHEMY_DATABASE_URI":"sqlite:///memory:",
        "TESTING":True,
    })