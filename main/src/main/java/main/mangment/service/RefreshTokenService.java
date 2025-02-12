package main.mangment.service;

import lombok.RequiredArgsConstructor;
import main.mangment.model.RefreshToken;
import main.mangment.model.User;
import main.mangment.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

/**
 * Сервис для работы с refresh-токенами.
 */
@Service
@RequiredArgsConstructor
public class RefreshTokenService {

	/**
	 * Время жизни refresh-токена в миллисекундах.
	 */
	@Value("${jwt.refreshExp}")
	private  int refreshTokenDurationMs;
	private final RefreshTokenRepository refreshTokenRepository;

	/**
	 * Поиск refresh-токена по значению токена.
	 *
	 * @param token значение токена
	 * @return найденный refresh-токен или null, если не найдено
	 */
	public RefreshToken findByRefreshToken(String token) {
		return refreshTokenRepository.findByRefreshToken(token);
	}

	/**
	 * Создание нового refresh-токена для указанного пользователя.
	 *
	 * @param user пользователь, для которого создается токен
	 * @return созданный refresh-токен
	 */
	public RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken = new RefreshToken();

		refreshToken.setUser(user);
		refreshToken.setExpDate(Instant.now().plusMillis(refreshTokenDurationMs));
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken = refreshTokenRepository.save(refreshToken);

		return refreshToken;
	}

	/**
	 * Проверка истечения срока действия refresh-токена.
	 *
	 * @param token проверяемый токен
	 * @return токен, если он действителен; null, если истек
	 */
	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(token);
			return null;
		}
		return token;
	}

	/**
	 * Удаление всех refresh-токенов, связанных с указанным пользователем.
	 *
	 * @param user пользователь, чьи токены нужно удалить
	 * @return количество удаленных токенов
	 */
	@Transactional
	public int deleteByUserId(User user) {
		return refreshTokenRepository.deleteByUser(user);
	}
}
