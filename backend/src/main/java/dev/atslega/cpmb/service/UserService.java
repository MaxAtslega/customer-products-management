package dev.atslega.cpmb.service;

import dev.atslega.cpmb.exception.ResourceAlreadyExistsException;
import dev.atslega.cpmb.exception.ResourceNotFoundException;
import dev.atslega.cpmb.model.User;
import dev.atslega.cpmb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUser(Long companyId) {
        return userRepository.findAll().stream().filter(c -> Objects.equals(c.getCompany().getId(), companyId)).toList();
    }

    public Optional<User> getUserById(Long id, Long companyId) {
        abortIfProductDoesNotExist(id, companyId);
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        if (userRepository.existsByEmail(user.getEmail()))
            throw new ResourceAlreadyExistsException("User", user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void deleteUser(Long id, Long companyId) {
        abortIfProductDoesNotExist(id, companyId);
        userRepository.deleteById(id);
    }

    public User updateUser(User user, Long companyId) {
        abortIfProductDoesNotExist(user.getId(), companyId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    private void abortIfProductDoesNotExist(Long id, Long companyId) {
        userRepository.findById(id).filter(c -> Objects.equals(c.getCompany().getId(), companyId)).orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), id));
    }
}
