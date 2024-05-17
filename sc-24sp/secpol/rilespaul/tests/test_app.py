import pytest
from app import create_app,db,task

@pytest.fixture
def client():
    app = create_app()
    app.config.update({
        "SQLALCHEMY_DATABASE_URI":"sqlite:///memory:",
        "TESTING":True,
    })

    with app.test_client() as client:
        with app.app_context():
            db.create_all()
            yield client

    def test_create_task(client):
        response = client.post("/tasks",json={"Title":"New Task"})
        assert response.status_code == 200
        assert b"New Task" in response.data
        assert task.query.count() == 1
