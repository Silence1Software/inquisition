package br.com.lbenaducci.inquisition.domain.player;

import br.com.lbenaducci.inquisition.domain.exception.NotFoundException;
import br.com.lbenaducci.inquisition.domain.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlayerUseCaseTest {
	private final PlayerRepository playerRepository = mock(PlayerRepository.class);
	private final PlayerUseCase useCase = new PlayerUseCase(playerRepository);

	@Nested
	class Create {
		@BeforeEach
		void setup() {
			doAnswer(i -> i.getArgument(0)).when(playerRepository).save(any());
		}

		@Test
		void givenName_whenCreate_thenCreatePlayer() {
			String playerName = "player";

			Player player = assertDoesNotThrow(() -> useCase.create(playerName));

			assertNotNull(player.getId());
			assertNotNull(player.getCreatedAt());
			assertEquals(playerName, player.getName());

			verify(playerRepository).save(player);
		}

		@Test
		void givenNullName_whenCreate_thenThrowException() {
			assertThrows(ValidationException.class, () -> useCase.create(null));
			verify(playerRepository, never()).save(any());
		}

		@Test
		void givenEmptyName_whenCreate_thenThrowException() {
			assertThrows(ValidationException.class, () -> useCase.create(""));
			verify(playerRepository, never()).save(any());
		}

		@Test
		void givenNameTooLong_whenCreate_thenThrowException() {
			assertThrows(ValidationException.class, () -> useCase.create("a".repeat(256)));
			verify(playerRepository, never()).save(any());
		}

		@Test
		void givenNameTooShort_whenCreate_thenThrowException() {
			assertThrows(ValidationException.class, () -> useCase.create("a"));
			verify(playerRepository, never()).save(any());
		}
	}

	@Nested
	class Find {
		private final UUID id = UUID.randomUUID();

		@BeforeEach
		void setup() {
			Player player = new Player(id, LocalDateTime.now(), "player");
			doReturn(Optional.of(player)).when(playerRepository).findById(id);
		}

		@Test
		void givenId_whenFind_thenFindPlayer() {
			Player player = assertDoesNotThrow(() -> useCase.find(id));

			assertNotNull(player);
			assertEquals(id, player.getId());

			verify(playerRepository).findById(id);
		}

		@Test
		void givenNullId_whenFind_thenThrowException() {
			assertThrows(ValidationException.class, () -> useCase.find(null));
			verify(playerRepository, never()).findById(any());
		}

		@Test
		void givenNonexistentId_whenFind_thenThrowException() {
			assertThrows(NotFoundException.class, () -> useCase.find(UUID.randomUUID()));
			verify(playerRepository).findById(any());
		}
	}

	@Nested
	class ChangeName {
		private final UUID id = UUID.randomUUID();
		private final String oldName = "oldName";

		@BeforeEach
		void setup() {
			doAnswer(i -> i.getArgument(0)).when(playerRepository).save(any());
			doReturn(Optional.of(new Player(id, LocalDateTime.now(), oldName))).when(playerRepository).findById(id);
		}

		@Test
		void givenName_whenChangeName_thenChangeName() {
			String playerName = "player";
			Player player = new Player(id, LocalDateTime.now(), oldName);

			Player result = assertDoesNotThrow(() -> useCase.changeName(player.getId(), playerName));

			assertEquals(player.getId(), result.getId());
			assertEquals(playerName, result.getName());

			verify(playerRepository).findById(any());
			verify(playerRepository).save(any());
		}

		@Test
		void givenNullName_whenChangeName_thenThrowException() {
			assertThrows(ValidationException.class, () -> useCase.changeName(id, null));

			verify(playerRepository, never()).findById(any());
			verify(playerRepository, never()).save(any());
		}

		@Test
		void givenEmptyName_whenChangeName_thenThrowException() {
			assertThrows(ValidationException.class, () -> useCase.changeName(id, ""));

			verify(playerRepository).findById(any());
			verify(playerRepository, never()).save(any());
		}

		@Test
		void givenNameTooLong_whenChangeName_thenThrowException() {
			assertThrows(ValidationException.class, () -> useCase.changeName(id, "a".repeat(256)));

			verify(playerRepository).findById(any());
			verify(playerRepository, never()).save(any());
		}

		@Test
		void givenNameTooShort_whenChangeName_thenThrowException() {
			assertThrows(ValidationException.class, () -> useCase.changeName(id, "a"));

			verify(playerRepository).findById(any());
			verify(playerRepository, never()).save(any());
		}

		@Test
		void givenNullId_whenChangeName_thenThrowException() {
			assertThrows(ValidationException.class, () -> useCase.changeName(null, "player"));

			verify(playerRepository, never()).findById(any());
			verify(playerRepository, never()).save(any());
		}

		@Test
		void givenNonexistentId_whenChangeName_thenThrowException() {
			assertThrows(NotFoundException.class, () -> useCase.changeName(UUID.randomUUID(), "player"));

			verify(playerRepository).findById(any());
			verify(playerRepository, never()).save(any());
		}
	}

	@Nested
	class Delete {

		@Test
		void givenId_whenDelete_thenDeletePlayer() {
			UUID id = UUID.randomUUID();
			assertDoesNotThrow(() -> useCase.delete(id));

			verify(playerRepository).deleteById(id);
		}

		@Test
		void givenNullId_whenDelete_thenThrowException() {
			assertThrows(ValidationException.class, () -> useCase.delete(null));
			verify(playerRepository, never()).deleteById(any());
		}
	}
}
