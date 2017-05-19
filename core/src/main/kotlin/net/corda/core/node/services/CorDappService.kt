package net.corda.core.node.services

import kotlin.annotation.AnnotationTarget.CLASS

/**
 * Annotate any class that needs to be a long-lived service within the node, such as an oracle, with this annotation.
 * Such a class needs to have a constructor with a single parameter of type [net.corda.core.node.PluginServiceHub]. This
 * construtor will be invoked during node start to initialise the service. The service hub provided can be used to get
 * information about the node that may be necessary for the service. CorDapp services are created as singletons within
 * the node and are available to flows via [net.corda.core.node.ServiceHub.cordappService].
 *
 * It is recommended that the service class extend [net.corda.core.serialization.SingletonSerializeAsToken] to ensure
 * it doesn't get serialised in flow checkpoints.
 */
@Target(CLASS)
annotation class CorDappService