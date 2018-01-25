package com.market.utility;

import java.io.Serializable;

public class MersenneTwisterFast implements Serializable {

	/**
	 * 生成随机数
	 */
	private static final long serialVersionUID = 1L;
	private static final int N = 624;
	private static final int M = 397;
	private static final int MATRIX_A = 0x9908b0df; // private static final *
													// constant vector a
	private static final int UPPER_MASK = 0x80000000; // most significant w-r
														// bits
	private static final int LOWER_MASK = 0x7fffffff; // least significant r
														// bits

	// Tempering parameters
	private static final int TEMPERING_MASK_B = 0x9d2c5680;
	private static final int TEMPERING_MASK_C = 0xefc60000;

	private int mt[]; // the array for the state vector
	private int mti; // mti==N+1 means mt[N] is not initialized
	private int mag01[];
	// a good initial seed (of int size, though stored in a long)
	private static final long GOOD_SEED = 4357;

	private double nextNextGaussian;
	private boolean haveNextNextGaussian;

	public MersenneTwisterFast() {
		setSeed(GOOD_SEED);
	}

	public MersenneTwisterFast(long seed) {
		setSeed(seed);
	}

	public final void setSeed(long seed) {
		haveNextNextGaussian = false;

		mt = new int[N];

		mt[0] = ((int) seed); // & 0xffffffff;

		for (mti = 1; mti < N; mti++)
			mt[mti] = (69069 * mt[mti - 1]); // & 0xffffffff;

		mag01 = new int[2];
		mag01[0] = 0x0;
		mag01[1] = MATRIX_A;
	}

	public final int nextInt() {
		int y;

		if (mti >= N) // generate N words at one time
		{
			int kk;

			for (kk = 0; kk < N - M; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			for (; kk < N - 1; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
			mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];

			mti = 0;
		}

		y = mt[mti++];
		y ^= y >>> 11; // TEMPERING_SHIFT_U(y)
		y ^= (y << 7) & TEMPERING_MASK_B; // TEMPERING_SHIFT_S(y)
		y ^= (y << 15) & TEMPERING_MASK_C; // TEMPERING_SHIFT_T(y)
		y ^= (y >>> 18); // TEMPERING_SHIFT_L(y)

		return y;
	}

	public final short nextShort() {
		int y;

		if (mti >= N) // generate N words at one time
		{
			int kk;

			for (kk = 0; kk < N - M; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			for (; kk < N - 1; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
			mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];

			mti = 0;
		}

		y = mt[mti++];
		y ^= y >>> 11; // TEMPERING_SHIFT_U(y)
		y ^= (y << 7) & TEMPERING_MASK_B; // TEMPERING_SHIFT_S(y)
		y ^= (y << 15) & TEMPERING_MASK_C; // TEMPERING_SHIFT_T(y)
		y ^= (y >>> 18); // TEMPERING_SHIFT_L(y)

		return (short) (y >>> 16);
	}

	public final char nextChar() {
		int y;

		if (mti >= N) // generate N words at one time

		{
			int kk;

			for (kk = 0; kk < N - M; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			for (; kk < N - 1; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
			mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];

			mti = 0;
		}

		y = mt[mti++];
		y ^= y >>> 11; // TEMPERING_SHIFT_U(y)
		y ^= (y << 7) & TEMPERING_MASK_B; // TEMPERING_SHIFT_S(y)
		y ^= (y << 15) & TEMPERING_MASK_C; // TEMPERING_SHIFT_T(y)
		y ^= (y >>> 18); // TEMPERING_SHIFT_L(y)

		return (char) (y >>> 16);
	}

	public final boolean nextBoolean() {
		int y;

		if (mti >= N) // generate N words at one time
		{
			int kk;

			for (kk = 0; kk < N - M; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
			}

			for (; kk < N - 1; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);

				mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
			mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];

			mti = 0;
		}

		y = mt[mti++];
		y ^= y >>> 11; // TEMPERING_SHIFT_U(y)
		y ^= (y << 7) & TEMPERING_MASK_B; // TEMPERING_SHIFT_S(y)
		y ^= (y << 15) & TEMPERING_MASK_C; // TEMPERING_SHIFT_T(y)
		y ^= (y >>> 18); // TEMPERING_SHIFT_L(y)

		return (boolean) ((y >>> 31) != 0);
	}

	public final byte nextByte() {
		int y;

		if (mti >= N) // generate N words at one time
		{
			int kk;

			for (kk = 0; kk < N - M; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			for (; kk < N - 1; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
			mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];

			mti = 0;
		}

		y = mt[mti++];
		y ^= y >>> 11; // TEMPERING_SHIFT_U(y)
		y ^= (y << 7) & TEMPERING_MASK_B; // TEMPERING_SHIFT_S(y)
		y ^= (y << 15) & TEMPERING_MASK_C; // TEMPERING_SHIFT_T(y)
		y ^= (y >>> 18); // TEMPERING_SHIFT_L(y)

		return (byte) (y >>> 24);
	}

	public final void nextBytes(byte[] bytes) {
		int y;

		for (int x = 0; x < bytes.length; x++) {
			if (mti >= N) // generate N words at one time
			{
				int kk;

				for (kk = 0; kk < N - M; kk++) {
					y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
					mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
				}
				for (; kk < N - 1; kk++) {
					y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
					mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
				}
				y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
				mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];

				mti = 0;
			}

			y = mt[mti++];
			y ^= y >>> 11; // TEMPERING_SHIFT_U(y)
			y ^= (y << 7) & TEMPERING_MASK_B; // TEMPERING_SHIFT_S(y)
			y ^= (y << 15) & TEMPERING_MASK_C; // TEMPERING_SHIFT_T(y)
			y ^= (y >>> 18); // TEMPERING_SHIFT_L(y)

			bytes[x] = (byte) (y >>> 24);
		}
	}

	public final long nextLong() {
		int y;
		int z;

		if (mti >= N) // generate N words at one time
		{
			int kk;

			for (kk = 0; kk < N - M; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			for (; kk < N - 1; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
			mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];

			mti = 0;
		}

		y = mt[mti++];
		y ^= y >>> 11; // TEMPERING_SHIFT_U(y)
		y ^= (y << 7) & TEMPERING_MASK_B; // TEMPERING_SHIFT_S(y)
		y ^= (y << 15) & TEMPERING_MASK_C; // TEMPERING_SHIFT_T(y)
		y ^= (y >>> 18); // TEMPERING_SHIFT_L(y)

		if (mti >= N) // generate N words at one time

		{
			int kk;

			for (kk = 0; kk < N - M; kk++) {
				z = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + M] ^ (z >>> 1) ^ mag01[z & 0x1];
			}
			for (; kk < N - 1; kk++) {
				z = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + (M - N)] ^ (z >>> 1) ^ mag01[z & 0x1];
			}
			z = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
			mt[N - 1] = mt[M - 1] ^ (z >>> 1) ^ mag01[z & 0x1];

			mti = 0;
		}

		z = mt[mti++];
		z ^= z >>> 11; // TEMPERING_SHIFT_U(z)
		z ^= (z << 7) & TEMPERING_MASK_B; // TEMPERING_SHIFT_S(z)
		z ^= (z << 15) & TEMPERING_MASK_C; // TEMPERING_SHIFT_T(z)
		z ^= (z >>> 18); // TEMPERING_SHIFT_L(z)

		return (((long) y) << 32) + (long) z;
	}

	public final double nextDouble() {
		int y;
		int z;

		if (mti >= N) // generate N words at one time
		{
			int kk;

			for (kk = 0; kk < N - M; kk++) {

				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			for (; kk < N - 1; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
			mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];

			mti = 0;
		}

		y = mt[mti++];
		y ^= y >>> 11; // TEMPERING_SHIFT_U(y)
		y ^= (y << 7) & TEMPERING_MASK_B; // TEMPERING_SHIFT_S(y)
		y ^= (y << 15) & TEMPERING_MASK_C; // TEMPERING_SHIFT_T(y)
		y ^= (y >>> 18); // TEMPERING_SHIFT_L(y)

		if (mti >= N) // generate N words at one time
		{
			int kk;

			for (kk = 0; kk < N - M; kk++) {
				z = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + M] ^ (z >>> 1) ^ mag01[z & 0x1];
			}
			for (; kk < N - 1; kk++) {
				z = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + (M - N)] ^ (z >>> 1) ^ mag01[z & 0x1];
			}
			z = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);

			mt[N - 1] = mt[M - 1] ^ (z >>> 1) ^ mag01[z & 0x1];

			mti = 0;
		}

		z = mt[mti++];
		z ^= z >>> 11; // TEMPERING_SHIFT_U(z)
		z ^= (z << 7) & TEMPERING_MASK_B; // TEMPERING_SHIFT_S(z)
		z ^= (z << 15) & TEMPERING_MASK_C; // TEMPERING_SHIFT_T(z)
		z ^= (z >>> 18); // TEMPERING_SHIFT_L(z)

		/* derived from nextDouble documentation in jdk 1.2 docs, see top */
		return ((((long) (y >>> 6)) << 27) + (z >>> 5)) / (double) (1L << 53);
	}

	public final double nextGaussian() {
		if (haveNextNextGaussian) {
			haveNextNextGaussian = false;
			return nextNextGaussian;
		} else {
			double v1, v2, s;
			do {
				int y;
				int z;
				int a;
				int b;

				if (mti >= N) // generate N words at one time
				{
					int kk;

					for (kk = 0; kk < N - M; kk++)

					{
						y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
						mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
					}
					for (; kk < N - 1; kk++) {
						y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
						mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
					}
					y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);

					mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];

					mti = 0;
				}

				y = mt[mti++];
				y ^= y >>> 11; // TEMPERING_SHIFT_U(y)
				y ^= (y << 7) & TEMPERING_MASK_B; // TEMPERING_SHIFT_S(y)
				y ^= (y << 15) & TEMPERING_MASK_C; // TEMPERING_SHIFT_T(y)
				y ^= (y >>> 18); // TEMPERING_SHIFT_L(y)

				if (mti >= N) // generate N words at one time
				{
					int kk;

					for (kk = 0; kk < N - M; kk++) {
						z = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
						mt[kk] = mt[kk + M] ^ (z >>> 1) ^ mag01[z & 0x1];
					}
					for (; kk < N - 1; kk++) {
						z = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
						mt[kk] = mt[kk + (M - N)] ^ (z >>> 1) ^ mag01[z & 0x1];
					}
					z = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
					mt[N - 1] = mt[M - 1] ^ (z >>> 1) ^ mag01[z & 0x1];

					mti = 0;
				}

				z = mt[mti++];
				z ^= z >>> 11; // TEMPERING_SHIFT_U(z)
				z ^= (z << 7) & TEMPERING_MASK_B; // TEMPERING_SHIFT_S(z)
				z ^= (z << 15) & TEMPERING_MASK_C; // TEMPERING_SHIFT_T(z)
				z ^= (z >>> 18); // TEMPERING_SHIFT_L(z)

				if (mti >= N) // generate N words at one time
				{
					int kk;

					for (kk = 0; kk < N - M; kk++) {
						a = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
						mt[kk] = mt[kk + M] ^ (a >>> 1) ^ mag01[a & 0x1];
					}
					for (; kk < N - 1; kk++) {

						a = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
						mt[kk] = mt[kk + (M - N)] ^ (a >>> 1) ^ mag01[a & 0x1];
					}
					a = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
					mt[N - 1] = mt[M - 1] ^ (a >>> 1) ^ mag01[a & 0x1];

					mti = 0;
				}

				a = mt[mti++];
				a ^= a >>> 11; // TEMPERING_SHIFT_U(a)
				a ^= (a << 7) & TEMPERING_MASK_B; // TEMPERING_SHIFT_S(a)
				a ^= (a << 15) & TEMPERING_MASK_C; // TEMPERING_SHIFT_T(a)
				a ^= (a >>> 18); // TEMPERING_SHIFT_L(a)
				if (mti >= N) // generate N words at one time
				{
					int kk;

					for (kk = 0; kk < N - M; kk++) {
						b = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
						mt[kk] = mt[kk + M] ^ (b >>> 1) ^ mag01[b & 0x1];
					}
					for (; kk < N - 1; kk++) {
						b = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
						mt[kk] = mt[kk + (M - N)] ^ (b >>> 1) ^ mag01[b & 0x1];
					}
					b = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
					mt[N - 1] = mt[M - 1] ^ (b >>> 1) ^ mag01[b & 0x1];

					mti = 0;
				}

				b = mt[mti++];
				b ^= b >>> 11; // TEMPERING_SHIFT_U(b)
				b ^= (b << 7) & TEMPERING_MASK_B; // TEMPERING_SHIFT_S(b)
				b ^= (b << 15) & TEMPERING_MASK_C; // TEMPERING_SHIFT_T(b)
				b ^= (b >>> 18); // TEMPERING_SHIFT_L(b)

				/*
				 * derived from nextDouble documentation in jdk 1.2 docs, see
				 * top
				 */
				v1 = 2 * (((((long) (y >>> 6)) << 27) + (z >>> 5)) / (double) (1L << 53)) - 1;
				v2 = 2 * (((((long) (a >>> 6)) << 27) + (b >>> 5)) / (double) (1L << 53)) - 1;
				s = v1 * v1 + v2 * v2;
			} while (s >= 1);
			double multiplier = Math.sqrt(-2 * Math.log(s) / s);
			nextNextGaussian = v2 * multiplier;
			haveNextNextGaussian = true;
			return v1 * multiplier;
		}
	}

	public final float nextFloat() {
		int y;

		if (mti >= N) // generate N words at one time
		{
			int kk;

			for (kk = 0; kk < N - M; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			for (; kk < N - 1; kk++) {
				y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
				mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
			}
			y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
			mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];

			mti = 0;

		}

		y = mt[mti++];
		y ^= y >>> 11; // TEMPERING_SHIFT_U(y)
		y ^= (y << 7) & TEMPERING_MASK_B; // TEMPERING_SHIFT_S(y)
		y ^= (y << 15) & TEMPERING_MASK_C; // TEMPERING_SHIFT_T(y)
		y ^= (y >>> 18); // TEMPERING_SHIFT_L(y)

		return (y >>> 8) / ((float) (1 << 24));
	}

	public int nextInt(int n) {
		if (n <= 0)
			throw new IllegalArgumentException("n must be positive");

		if ((n & -n) == n) // i.e., n is a power of 2
		{
			int y;

			if (mti >= N) // generate N words at one time
			{
				int kk;

				for (kk = 0; kk < N - M; kk++) {
					y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
					mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
				}
				for (; kk < N - 1; kk++) {
					y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
					mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
				}
				y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
				mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];

				mti = 0;
			}

			y = mt[mti++];
			y ^= y >>> 11; // TEMPERING_SHIFT_U(y)
			y ^= (y << 7) & TEMPERING_MASK_B; // TEMPERING_SHIFT_S(y)
			y ^= (y << 15) & TEMPERING_MASK_C; // TEMPERING_SHIFT_T(y)
			y ^= (y >>> 18); // TEMPERING_SHIFT_L(y)

			return (int) ((n * (long) (y >>> 1)) >> 31);
		}

		int bits, val;
		do {
			int y;

			if (mti >= N) // generate N words at one time
			{
				int kk;

				for (kk = 0; kk < N - M; kk++) {
					y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
					mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
				}
				for (; kk < N - 1; kk++) {
					y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
					mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
				}
				y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
				mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];

				mti = 0;
			}

			y = mt[mti++];
			y ^= y >>> 11; // TEMPERING_SHIFT_U(y)
			y ^= (y << 7) & TEMPERING_MASK_B; // TEMPERING_SHIFT_S(y)
			y ^= (y << 15) & TEMPERING_MASK_C; // TEMPERING_SHIFT_T(y)
			y ^= (y >>> 18); // TEMPERING_SHIFT_L(y)

			bits = (y >>> 1);
			val = bits % n;
		} while (bits - val + (n - 1) < 0);
		return val;
	}
	
	public static void main(String[] args) {

		MersenneTwisterFast r=new MersenneTwisterFast(System.currentTimeMillis());
//		while(true){
//			int n1=r.nextInt(3);
//			System.out.println(n1);
//		}
		

//		double k=0;
//		double k1=0;
//		int num=100;
//		for (int i = 0; i < num; i++) {
////			int n1=r.nextInt(100);
//			double n2=r.nextDouble();
//			double n3=Math.random();
//			k+=n2;
//			k1+=n3;
//			System.out.println(n2+"==="+n3);
//		}
//		System.out.println("dddd"+k/num);
//		System.out.println("dddd"+k1/num);
		
		
		for (int i = 0;i<1000 ;i++ ) {
			System.out.println(r.nextInt(100));	

		}
		
		
//			double n=r.nextDouble();
//			System.out.println(n);
		
		
	}

}
