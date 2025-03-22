package security;

import java.util.UUID;

public record UserDTO(UUID id, String username, String roles)
{
}
