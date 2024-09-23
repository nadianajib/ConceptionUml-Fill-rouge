package com.example.CleanDar.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        // Extraction de l'en-tête d'autorisation
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Vérification de l'en-tête d'autorisation
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("Authorization header is missing or does not start with Bearer");
            filterChain.doFilter(request, response);
            return;
        }

        // Extraction du JWT (le token commence après "Bearer ")
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        // Vérification que l'email de l'utilisateur est présent et qu'il n'est pas déjà authentifié
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // Vérification que le token est valide
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // Création d'un objet UsernamePasswordAuthenticationToken pour l'utilisateur authentifié
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // Mise à jour du contexte de sécurité
                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("User authenticated: " + userEmail);
            } else {
                System.out.println("JWT is invalid or expired");
            }
        }

        // Passer à la suite des filtres
        filterChain.doFilter(request, response);
    }
}
