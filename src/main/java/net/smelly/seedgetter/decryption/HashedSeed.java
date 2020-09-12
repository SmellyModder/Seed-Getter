package net.smelly.seedgetter.decryption;

import com.google.common.hash.Hashing;

import java.util.function.Predicate;

public final class HashedSeed implements Predicate<Long> {
	private final long hashedSeed;

	public HashedSeed(final long hashedSeed) {
		this.hashedSeed = hashedSeed;
	}

	@Override
	public boolean test(Long otherSeed) {
		return Hashing.sha256().hashLong(otherSeed).asLong() == this.hashedSeed;
	}
}
