from django.shortcuts import redirect
from django.utils.deprecation import MiddlewareMixin

class CatchPageNotFoundErrorMiddleware(MiddlewareMixin):
    def process_response(self, request, response):
        if response.status_code == 404:
            return redirect('login_view')
        return response