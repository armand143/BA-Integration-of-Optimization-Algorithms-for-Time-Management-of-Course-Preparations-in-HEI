from django.contrib.auth.backends import BaseBackend
from .models import CustomUser

class CustomAuthBackend(BaseBackend):
    def authenticate(self, request, username=None, password=None, **kwargs):
        try:
            user = CustomUser.objects.get(username=username)
            if password is not None:
                if user.check_password(password):
                    return user
            else:
                print("Password cannot be None")
        except CustomUser.DoesNotExist:
            return None

    def get_user(self, user_id):
        try:
            return CustomUser.objects.get(pk=user_id)
        except CustomUser.DoesNotExist:
            return None
