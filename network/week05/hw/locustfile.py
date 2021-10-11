import random
from locust import HttpUser, task, between

class QuickStartUser(HttpUser):
    wait_time = between(1, 10)

    @task
    def index_page(self):
        self.client.get("/")
        
    @task
    def view_item(self):
        item_id = random.randint(1, 10000)
        self.client.get("/")

