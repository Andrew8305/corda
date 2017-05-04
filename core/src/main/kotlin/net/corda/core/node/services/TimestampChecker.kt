package net.corda.core.node.services

import net.corda.core.contracts.Timestamp
import net.corda.core.seconds
import net.corda.core.until
import net.corda.core.utilities.loggerFor
import java.time.Clock
import java.time.Duration

/**
 * Checks if the given timestamp falls within the allowed tolerance interval.
 */
class TimestampChecker(val clock: Clock = Clock.systemUTC(),
                       val tolerance: Duration = 30.seconds) {
    private companion object {
        val logger = loggerFor<TimestampChecker>()
    }

    fun isValid(timestampCommand: Timestamp): Boolean {
        val before = timestampCommand.before
        val after = timestampCommand.after

        val now = clock.instant()

        logger.info("Checking timestamp - before: $before, after: $after, now: $now")

        // We don't need to test for (before == null && after == null) or backwards bounds because the TimestampCommand
        // constructor already checks that.
        if (before != null && before until now > tolerance) return false
        if (after != null && now until after > tolerance) return false
        return true
    }
}
