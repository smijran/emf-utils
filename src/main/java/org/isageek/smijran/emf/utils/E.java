package org.isageek.smijran.emf.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

/**
 * Library class containing Java 8 {@link Predicate}s, {@link Function}s, {@link Consumer}s and
 * {@link Comparator}s that can be usable for {@link Stream} operations on {@link EObject} {@link Collection}
 * and {@link Iterable}.
 *
 * @author created: kszalkowski on 20 maj 2015 20:32:08
 * @author last change: $Author: $ on $Date: $
 * @version $Revision: $
 */
public final class E
{
    private E()
    {
        // Aggregating class
    }

    /**
     * Internal class contains different {@link Predicate}s for {@link EObject}s.
     *
     * @author created: kszalkowski on 20 maj 2015 20:35:51
     * @author last change: $Author: $ on $Date: $
     * @version $Revision: $
     */
    public final static class Predicates
    {
        private Predicates()
        {
            // Factory class
        }

        /**
         * Tests whether {@link EObject} has a primary key.
         * 
         * @return {@link Predicate} which tests whether wrapper has primary key.
         */
        public static Predicate< EObject > hasId()
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aObject )
                {
                    return aObject.eClass().getEIDAttribute() != null
                        && aObject.eIsSet( aObject.eClass().getEIDAttribute() );
                }
            };
        }

        /**
         * Tests whether given {@link EObject} has property.
         * 
         * @param aProperty
         *            Property name to be tested.
         * @return {@link Predicate} which tests whether wrapper has given property.
         */
        public static Predicate< EObject > hasProperty( String aProperty )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aObject )
                {
                    return aObject.eClass().getEStructuralFeature( aProperty ) != null;
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is null.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Predicate} which tests whether wrapper's property value is <code>null</code>.
         */
        public static Predicate< EObject > valueIsNull( String aProperty )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    return aInput.eGet( aInput.eClass().getEStructuralFeature( aProperty ) ) == null;
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is null.
         * 
         * @param aProperty
         *            Property.
         * @return {@link Predicate} which tests whether wrapper's property value is <code>null</code>.
         */
        public static Predicate< EObject > valueIsNull( EStructuralFeature aProperty )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    return aInput.eGet( aProperty ) == null;
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is equal to given object.
         * 
         * @param aProperty
         *            Property name.
         * @param aValue
         *            Value to be tested against value.
         * @return {@link Predicate} which tests whether wrapper's property value is equal to
         *         <code>aValue</code> .
         */
        public static Predicate< EObject > valueEq( String aProperty, int aValue )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    final int value = (int)aInput.eGet( aInput.eClass().getEStructuralFeature( aProperty ) );
                    return value == aValue;
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is equal to given object.
         * 
         * @param aProperty
         *            Property name.
         * @param aValue
         *            Value to be tested against value.
         * @return {@link Predicate} which tests whether wrapper's property value is equal to
         *         <code>aValue</code> .
         */
        public static Predicate< EObject > valueEq( String aProperty, long aValue )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    final long value = (long)aInput.eGet( aInput.eClass().getEStructuralFeature( aProperty ) );
                    return value == aValue;
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is equal to given object.
         * 
         * @param aProperty
         *            Property name.
         * @param aValue
         *            Value to be tested against value.
         * @return {@link Predicate} which tests whether wrapper's property value is equal to
         *         <code>aValue</code> .
         */
        public static Predicate< EObject > valueEq( String aProperty, double aValue )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    final double value =
                        (double)aInput.eGet( aInput.eClass().getEStructuralFeature( aProperty ) );
                    return value == aValue;
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is equal to given object.
         * 
         * @param aProperty
         *            Property name.
         * @param aValue
         *            Value to be tested against value.
         * @return {@link Predicate} which tests whether wrapper's property value is equal to
         *         <code>aValue</code> .
         */
        public static Predicate< EObject > valueEq( String aProperty, float aValue )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    final float value =
                        (float)aInput.eGet( aInput.eClass().getEStructuralFeature( aProperty ) );
                    return value == aValue;
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is equal to given object.
         * 
         * @param aProperty
         *            Property name.
         * @param aValue
         *            Value to be tested against value.
         * @return {@link Predicate} which tests whether wrapper's property value is equal to
         *         <code>aValue</code> .
         */
        public static Predicate< EObject > valueEq( String aProperty, short aValue )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    final short value =
                        (short)aInput.eGet( aInput.eClass().getEStructuralFeature( aProperty ) );
                    return value == aValue;
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is equal to given object.
         * 
         * @param aProperty
         *            Property name.
         * @param aValue
         *            Value to be tested against value.
         * @return {@link Predicate} which tests whether wrapper's property value is equal to
         *         <code>aValue</code> .
         */
        public static Predicate< EObject > valueEq( String aProperty, char aValue )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    final char value = (char)aInput.eGet( aInput.eClass().getEStructuralFeature( aProperty ) );
                    return value == aValue;
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is equal to given object.
         * 
         * @param aProperty
         *            Property name.
         * @param aObject
         *            Object to be tested against value.
         * @return {@link Predicate} which tests whether wrapper's property value is equal to
         *         <code>aObject</code> .
         */
        public static Predicate< EObject > valueEq( String aProperty, Object aObject )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    return Objects.equal( aObject,
                        aInput.eGet( aInput.eClass().getEStructuralFeature( aProperty ) ) );
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is equal to given object.
         * 
         * @param aProperty
         *            Property name.
         * @param aValue
         *            Value to be tested against value.
         * @return {@link Predicate} which tests whether wrapper's property value is equal to
         *         <code>aValue</code> .
         */
        public static Predicate< EObject > valueEq( EStructuralFeature aProperty, int aValue )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    final int value = (int)aInput.eGet( aProperty );
                    return value == aValue;
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is equal to given object.
         * 
         * @param aProperty
         *            Property name.
         * @param aValue
         *            Value to be tested against value.
         * @return {@link Predicate} which tests whether wrapper's property value is equal to
         *         <code>aValue</code> .
         */
        public static Predicate< EObject > valueEq( EStructuralFeature aProperty, long aValue )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    final long value = (long)aInput.eGet( aProperty );
                    return value == aValue;
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is equal to given object.
         * 
         * @param aProperty
         *            Property name.
         * @param aValue
         *            Value to be tested against value.
         * @return {@link Predicate} which tests whether wrapper's property value is equal to
         *         <code>aValue</code> .
         */
        public static Predicate< EObject > valueEq( EStructuralFeature aProperty, double aValue )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    final double value = (double)aInput.eGet( aProperty );
                    return value == aValue;
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is equal to given object.
         * 
         * @param aProperty
         *            Property name.
         * @param aValue
         *            Value to be tested against value.
         * @return {@link Predicate} which tests whether wrapper's property value is equal to
         *         <code>aValue</code> .
         */
        public static Predicate< EObject > valueEq( EStructuralFeature aProperty, float aValue )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    final float value = (float)aInput.eGet( aProperty );
                    return value == aValue;
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is equal to given object.
         * 
         * @param aProperty
         *            Property name.
         * @param aValue
         *            Value to be tested against value.
         * @return {@link Predicate} which tests whether wrapper's property value is equal to
         *         <code>aValue</code> .
         */
        public static Predicate< EObject > valueEq( EStructuralFeature aProperty, short aValue )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    final short value = (short)aInput.eGet( aProperty );
                    return value == aValue;
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is equal to given object.
         * 
         * @param aProperty
         *            Property name.
         * @param aValue
         *            Value to be tested against value.
         * @return {@link Predicate} which tests whether wrapper's property value is equal to
         *         <code>aValue</code> .
         */
        public static Predicate< EObject > valueEq( EStructuralFeature aProperty, char aValue )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    final char value = (char)aInput.eGet( aProperty );
                    return value == aValue;
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is equal to given object.
         * 
         * @param aProperty
         *            Property name.
         * @param aObject
         *            Object to be tested against value.
         * @return {@link Predicate} which tests whether wrapper's property value is equal to
         *         <code>aObject</code> .
         */
        public static Predicate< EObject > valueEq( EStructuralFeature aProperty, Object aObject )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    return Objects.equal( aObject, aInput.eGet( aProperty ) );
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is between given bounds.
         * 
         * @param aProperty
         *            Property name.
         * @param aLowerBound
         *            Lower bound to be tested against value.
         * @param aUpperBound
         *            Upper exclusive bound to be tested against value.
         * @return {@link Predicate} which tests whether wrapper's property value is equal to
         *         <code>aObject</code> .
         */
        public static < T extends Comparable< T >>Predicate< EObject > valueBetween( String aString,
            Comparable< T > aLowerBound, Comparable< T > aUpperBound )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    @SuppressWarnings( "unchecked" )
                    final T value = (T)aInput.eGet( aInput.eClass().getEStructuralFeature( aString ) );
                    return aLowerBound.compareTo( value ) >= 0 && aUpperBound.compareTo( value ) < 0;
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is between given bounds.
         * 
         * @param aProperty
         *            Property name.
         * @param aLowerBound
         *            Lower bound to be tested against value.
         * @param aUpperBound
         *            Lower bound to be tested against value.
         * @return {@link Predicate} which tests whether wrapper's property value is equal to
         *         <code>aObject</code> .
         */
        public static < T extends Comparable< T >>Predicate< EObject > valueBetween( String aString,
            int aLowerBound, int aUpperBound )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    final int value = (int)aInput.eGet( aInput.eClass().getEStructuralFeature( aString ) );
                    return aLowerBound <= value && value < aUpperBound;
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is between given bounds.
         * 
         * @param aProperty
         *            Property name.
         * @param aLowerBound
         *            Lower bound to be tested against value.
         * @param aUpperBound
         *            Lower bound to be tested against value.
         * @return {@link Predicate} which tests whether wrapper's property value is equal to
         *         <code>aObject</code> .
         */
        public static < T extends Comparable< T >>Predicate< EObject > valueBetween( String aString,
            long aLowerBound, long aUpperBound )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    final long value = (long)aInput.eGet( aInput.eClass().getEStructuralFeature( aString ) );
                    return aLowerBound <= value && value < aUpperBound;
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is between given bounds.
         * 
         * @param aProperty
         *            Property name.
         * @param aLowerBound
         *            Lower bound to be tested against value.
         * @param aUpperBound
         *            Lower bound to be tested against value.
         * @return {@link Predicate} which tests whether wrapper's property value is equal to
         *         <code>aObject</code> .
         */
        public static < T extends Comparable< T >>Predicate< EObject > valueBetween( String aString,
            double aLowerBound, double aUpperBound )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    final double value =
                        (double)aInput.eGet( aInput.eClass().getEStructuralFeature( aString ) );
                    return aLowerBound <= value && value < aUpperBound;
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is between given bounds.
         * 
         * @param aProperty
         *            Property name.
         * @param aLowerBound
         *            Lower bound to be tested against value.
         * @param aUpperBound
         *            Lower bound to be tested against value.
         * @return {@link Predicate} which tests whether wrapper's property value is equal to
         *         <code>aObject</code> .
         */
        public static < T extends Comparable< T >>Predicate< EObject > valueBetween( String aString,
            float aLowerBound, float aUpperBound )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    final float value = (float)aInput.eGet( aInput.eClass().getEStructuralFeature( aString ) );
                    return aLowerBound <= value && value < aUpperBound;
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is between given bounds.
         * 
         * @param aProperty
         *            Property name.
         * @param aLowerBound
         *            Lower bound to be tested against value.
         * @param aUpperBound
         *            Lower bound to be tested against value.
         * @return {@link Predicate} which tests whether wrapper's property value is equal to
         *         <code>aObject</code> .
         */
        public static < T extends Comparable< T >>Predicate< EObject > valueBetween( String aString,
            short aLowerBound, short aUpperBound )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    final short value = (short)aInput.eGet( aInput.eClass().getEStructuralFeature( aString ) );
                    return aLowerBound <= value && value < aUpperBound;
                }
            };
        }

        /**
         * Tests whether given {@link EObject}s property value is between given bounds.
         * 
         * @param aProperty
         *            Property name.
         * @param aLowerBound
         *            Lower bound to be tested against value.
         * @param aUpperBound
         *            Lower bound to be tested against value.
         * @return {@link Predicate} which tests whether wrapper's property value is equal to
         *         <code>aObject</code> .
         */
        public static < T extends Comparable< T >>Predicate< EObject > valueBetween( String aString,
            char aLowerBound, char aUpperBound )
        {
            return new Predicate< EObject >()
            {

                @Override
                public boolean test( EObject aInput )
                {
                    final char value = (char)aInput.eGet( aInput.eClass().getEStructuralFeature( aString ) );
                    return aLowerBound <= value && value < aUpperBound;
                }
            };
        }
    }

    /**
     * Internal class contains different {@link Predicate}s for {@link EObject}s.
     *
     * @author created: kszalkowski on 20 maj 2015 20:35:51
     * @author last change: $Author: $ on $Date: $
     * @version $Revision: $
     */
    public final static class Functions
    {
        private Functions()
        {
            // Factory class
        }

        /**
         * {@link Function} which extracts {@link CompositePkIf} primary key of {@link EObject}.
         * 
         * @return {@link Function} of given spec.
         */
        public static Function< EObject, Object > id()
        {
            return new Function< EObject, Object >()
            {

                @Override
                public Object apply( EObject aObject )
                {
                    Preconditions.checkArgument( Predicates.hasId().test( aObject ),
                        "Object class do not have id attribute." );
                    return aObject.eGet( aObject.eClass().getEIDAttribute() );
                }
            };
        }

        /**
         * {@link Function} which extracts {@link CompositePkIf} primary key single value from
         * {@link EObject}.
         * 
         * @param aPropertyName
         *            Property name of key.
         * @param aClazz
         *            Class for type safe casting.
         * @return {@link Function} of given spec.
         * @throws NullPointerException
         *             When <code>aClazz</code> is missing.
         * @throws IllegalArgumentException
         *             If primary key of wrapper is not present or is composite.
         */
        public static < T >Function< EObject, T > id( Class< T > aClazz )
        {
            Preconditions.checkNotNull( aClazz, "Clazz is missing." );
            return new Function< EObject, T >()
            {
                @Override
                public T apply( EObject aObject )
                {
                    Preconditions.checkArgument( Predicates.hasId().test( aObject ),
                        "Object class do not have id attribute." );
                    return aClazz.cast( aObject.eGet( aObject.eClass().getEIDAttribute() ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static < T >Function< EObject, T > value( String aProperty )
        {
            return new Function< EObject, T >()
            {

                @SuppressWarnings( "unchecked" )
                @Override
                public T apply( EObject aObject )
                {
                    final EStructuralFeature feature = aObject.eClass().getEStructuralFeature( aProperty );
                    Preconditions.checkState( feature != null, "Missing feature." );
                    return (T)aObject.eGet( feature );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} and performs typesafe case.
         * 
         * @param aProperty
         *            Property name.
         * @param aClass
         *            {@link Class} to perform safe cast to.
         * @return {@link Function} behaving as in spec.
         */
        public static < T >Function< EObject, T > value( String aProperty, Class< T > aClass )
        {
            return new Function< EObject, T >()
            {

                @Override
                public T apply( EObject aObject )
                {
                    final EStructuralFeature feature = aObject.eClass().getEStructuralFeature( aProperty );
                    Preconditions.checkState( feature != null, "Missing feature." );
                    return aClass.cast( aObject.eGet( feature ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link BigDecimal}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, BigDecimal > valBigDecimal( String aProperty )
        {
            return new Function< EObject, BigDecimal >()
            {

                @Override
                public BigDecimal apply( EObject aObject )
                {
                    final EStructuralFeature feature = aObject.eClass().getEStructuralFeature( aProperty );
                    Preconditions.checkState( feature != null, "Missing feature." );
                    return BigDecimal.class.cast( aObject.eGet( feature ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link BigInteger}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, BigInteger > valBigInteger( String aProperty )
        {
            return new Function< EObject, BigInteger >()
            {

                @Override
                public BigInteger apply( EObject aObject )
                {
                    final EStructuralFeature feature = aObject.eClass().getEStructuralFeature( aProperty );
                    Preconditions.checkState( feature != null, "Missing feature." );
                    return BigInteger.class.cast( aObject.eGet( feature ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link BigInteger}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, Date > valDate( String aProperty )
        {
            return new Function< EObject, Date >()
            {

                @Override
                public Date apply( EObject aObject )
                {
                    final EStructuralFeature feature = aObject.eClass().getEStructuralFeature( aProperty );
                    Preconditions.checkState( feature != null, "Missing feature." );
                    return Date.class.cast( aObject.eGet( feature ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link Integer}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, Integer > valInteger( String aProperty )
        {
            return new Function< EObject, Integer >()
            {

                @Override
                public Integer apply( EObject aObject )
                {
                    final EStructuralFeature feature = aObject.eClass().getEStructuralFeature( aProperty );
                    Preconditions.checkState( feature != null, "Missing feature." );
                    return Integer.class.cast( aObject.eGet( feature ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link Long}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, Long > valLong( String aProperty )
        {
            return new Function< EObject, Long >()
            {

                @Override
                public Long apply( EObject aObject )
                {
                    final EStructuralFeature feature = aObject.eClass().getEStructuralFeature( aProperty );
                    Preconditions.checkState( feature != null, "Missing feature." );
                    return Long.class.cast( aObject.eGet( feature ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link Short}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, Short > valShort( String aProperty )
        {
            return new Function< EObject, Short >()
            {

                @Override
                public Short apply( EObject aObject )
                {
                    final EStructuralFeature feature = aObject.eClass().getEStructuralFeature( aProperty );
                    Preconditions.checkState( feature != null, "Missing feature." );
                    return Short.class.cast( aObject.eGet( feature ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link Character}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, Character > valCharacter( String aProperty )
        {
            return new Function< EObject, Character >()
            {

                @Override
                public Character apply( EObject aObject )
                {
                    final EStructuralFeature feature = aObject.eClass().getEStructuralFeature( aProperty );
                    Preconditions.checkState( feature != null, "Missing feature." );
                    return Character.class.cast( aObject.eGet( feature ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link String}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, String > valString( String aProperty )
        {
            return new Function< EObject, String >()
            {

                @Override
                public String apply( EObject aObject )
                {
                    final EStructuralFeature feature = aObject.eClass().getEStructuralFeature( aProperty );
                    Preconditions.checkState( feature != null, "Missing feature." );
                    return String.class.cast( aObject.eGet( feature ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link IntStream}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, IntStream > valIntStream( String aProperty )
        {
            return new Function< EObject, IntStream >()
            {

                @Override
                public IntStream apply( EObject aObject )
                {
                    final EStructuralFeature feature = aObject.eClass().getEStructuralFeature( aProperty );
                    Preconditions.checkState( feature != null, "Missing feature." );
                    return IntStream.of( Integer.class.cast( aObject.eGet( feature ) ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link LongStream}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, LongStream > valLongStream( String aProperty )
        {
            return new Function< EObject, LongStream >()
            {

                @Override
                public LongStream apply( EObject aObject )
                {
                    final EStructuralFeature feature = aObject.eClass().getEStructuralFeature( aProperty );
                    Preconditions.checkState( feature != null, "Missing feature." );
                    return LongStream.of( Long.class.cast( aObject.eGet( feature ) ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link DoubleStream}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, DoubleStream > valDoubleStream( String aProperty )
        {
            return new Function< EObject, DoubleStream >()
            {

                @Override
                public DoubleStream apply( EObject aObject )
                {
                    final EStructuralFeature feature = aObject.eClass().getEStructuralFeature( aProperty );
                    Preconditions.checkState( feature != null, "Missing feature." );
                    return DoubleStream.of( Double.class.cast( aObject.eGet( feature ) ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link IntStream}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static ToIntFunction< EObject > valToInt( String aProperty )
        {
            return new ToIntFunction< EObject >()
            {

                @Override
                public int applyAsInt( EObject aObject )
                {
                    final EStructuralFeature feature = aObject.eClass().getEStructuralFeature( aProperty );
                    Preconditions.checkState( feature != null, "Missing feature." );
                    return Integer.class.cast( aObject.eGet( feature ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link LongStream}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static ToLongFunction< EObject > valToLong( String aProperty )
        {
            return new ToLongFunction< EObject >()
            {

                @Override
                public long applyAsLong( EObject aObject )
                {
                    final EStructuralFeature feature = aObject.eClass().getEStructuralFeature( aProperty );
                    Preconditions.checkState( feature != null, "Missing feature." );
                    return Long.class.cast( aObject.eGet( feature ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as double.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static ToDoubleFunction< EObject > valToDouble( String aProperty )
        {
            return new ToDoubleFunction< EObject >()
            {

                @Override
                public double applyAsDouble( EObject aObject )
                {
                    final EStructuralFeature feature = aObject.eClass().getEStructuralFeature( aProperty );
                    Preconditions.checkState( feature != null, "Missing feature." );
                    return Double.class.cast( aObject.eGet( feature ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static < T >Function< EObject, T > value( EStructuralFeature aProperty )
        {
            return new Function< EObject, T >()
            {

                @SuppressWarnings( "unchecked" )
                @Override
                public T apply( EObject aObject )
                {
                    return (T)aObject.eGet( aProperty );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} and performs typesafe case.
         * 
         * @param aProperty
         *            Property name.
         * @param aClass
         *            {@link Class} to perform safe cast to.
         * @return {@link Function} behaving as in spec.
         */
        public static < T >Function< EObject, T > value( EStructuralFeature aProperty, Class< T > aClass )
        {
            return new Function< EObject, T >()
            {

                @Override
                public T apply( EObject aObject )
                {
                    return aClass.cast( aObject.eGet( aProperty ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link BigDecimal}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, BigDecimal > valBigDecimal( EStructuralFeature aProperty )
        {
            return new Function< EObject, BigDecimal >()
            {

                @Override
                public BigDecimal apply( EObject aObject )
                {
                    return BigDecimal.class.cast( aObject.eGet( aProperty ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link BigInteger}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, BigInteger > valBigInteger( EStructuralFeature aProperty )
        {
            return new Function< EObject, BigInteger >()
            {

                @Override
                public BigInteger apply( EObject aObject )
                {
                    return BigInteger.class.cast( aObject.eGet( aProperty ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link BigInteger}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, Date > valDate( EStructuralFeature aProperty )
        {
            return new Function< EObject, Date >()
            {

                @Override
                public Date apply( EObject aObject )
                {
                    return Date.class.cast( aObject.eGet( aProperty ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link Integer}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, Integer > valInteger( EStructuralFeature aProperty )
        {
            return new Function< EObject, Integer >()
            {

                @Override
                public Integer apply( EObject aObject )
                {
                    return Integer.class.cast( aObject.eGet( aProperty ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link Long}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, Long > valLong( EStructuralFeature aProperty )
        {
            return new Function< EObject, Long >()
            {

                @Override
                public Long apply( EObject aObject )
                {
                    return Long.class.cast( aObject.eGet( aProperty ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link Short}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, Short > valShort( EStructuralFeature aProperty )
        {
            return new Function< EObject, Short >()
            {

                @Override
                public Short apply( EObject aObject )
                {
                    return Short.class.cast( aObject.eGet( aProperty ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link Character}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, Character > valCharacter( EStructuralFeature aProperty )
        {
            return new Function< EObject, Character >()
            {

                @Override
                public Character apply( EObject aObject )
                {
                    return Character.class.cast( aObject.eGet( aProperty ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link String}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, String > valString( EStructuralFeature aProperty )
        {
            return new Function< EObject, String >()
            {

                @Override
                public String apply( EObject aObject )
                {
                    return String.class.cast( aObject.eGet( aProperty ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link IntStream}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, IntStream > valIntStream( EStructuralFeature aProperty )
        {
            return new Function< EObject, IntStream >()
            {

                @Override
                public IntStream apply( EObject aObject )
                {
                    return IntStream.of( Integer.class.cast( aObject.eGet( aProperty ) ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link LongStream}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, LongStream > valLongStream( EStructuralFeature aProperty )
        {
            return new Function< EObject, LongStream >()
            {

                @Override
                public LongStream apply( EObject aObject )
                {
                    return LongStream.of( Long.class.cast( aObject.eGet( aProperty ) ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link DoubleStream}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, DoubleStream > valDoubleStream( EStructuralFeature aProperty )
        {
            return new Function< EObject, DoubleStream >()
            {

                @Override
                public DoubleStream apply( EObject aObject )
                {
                    return DoubleStream.of( Double.class.cast( aObject.eGet( aProperty ) ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link IntStream}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static ToIntFunction< EObject > valToInt( EStructuralFeature aProperty )
        {
            return new ToIntFunction< EObject >()
            {

                @Override
                public int applyAsInt( EObject aObject )
                {
                    return Integer.class.cast( aObject.eGet( aProperty ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as {@link LongStream}.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static ToLongFunction< EObject > valToLong( EStructuralFeature aProperty )
        {
            return new ToLongFunction< EObject >()
            {

                @Override
                public long applyAsLong( EObject aObject )
                {
                    return Long.class.cast( aObject.eGet( aProperty ) );
                }
            };
        }

        /**
         * Extracts given property value from {@link EObject} as double.
         * 
         * @param aProperty
         *            Property name.
         * @return {@link Function} behaving as in spec.
         */
        public static ToDoubleFunction< EObject > valToDouble( EStructuralFeature aProperty )
        {
            return new ToDoubleFunction< EObject >()
            {

                @Override
                public double applyAsDouble( EObject aObject )
                {
                    return Double.class.cast( aObject.eGet( aProperty ) );
                }
            };
        }

        /**
         * Extracts {@link EClass} from {@link EObject}.
         * 
         * @return {@link Function} behaving as in spec.
         */
        public static Function< EObject, EClass > eClass()
        {
            return EObject::eClass;
        }
    }

    /**
     * Internal class contains different {@link Consumer}s for {@link EObject}s.
     *
     * @author created: kszalkowski on 20 maj 2015 20:35:51
     * @author last change: $Author: $ on $Date: $
     * @version $Revision: $
     */
    public final static class Consumers
    {
        private Consumers()
        {
            // Factory class
        }

        /**
         * Creates {@link Consumer} which sets value of given property for {@link EObject}.
         * 
         * @param aProperty
         *            Property name of property.
         * @param aValue
         *            Value to be set. Can be null.
         * @return {@link Consumer} accoding to given spec.
         */
        public static Consumer< EObject > setValue( String aProperty, Object aValue )
        {
            return new Consumer< EObject >()
            {

                @Override
                public void accept( EObject aObject )
                {
                    aObject.eSet( aObject.eClass().getEStructuralFeature( aProperty ), aValue );
                }
            };
        }

        /**
         * Creates {@link Consumer} which sets value of given property for {@link EObject}.
         * 
         * @param aProperty
         *            Property name of property.
         * @param aValue
         *            Value to be set. Can be null.
         * @return {@link Consumer} accoding to given spec.
         */
        public static Consumer< EObject > setValue( EStructuralFeature aProperty, Object aValue )
        {
            return new Consumer< EObject >()
            {

                @Override
                public void accept( EObject aObject )
                {
                    aObject.eSet( aProperty, aValue );
                }
            };
        }
    }

    /**
     * Internal class contains different {@link Comparator}s for {@link EObject}s.
     *
     * @author created: kszalkowski on 20 maj 2015 20:35:51
     * @author last change: $Author: $ on $Date: $
     * @version $Revision: $
     */
    public final static class Comparators
    {
        private Comparators()
        {
            // Factory class
        }

        /**
         * Comparator base on properties with given name.
         * 
         * @param aProperty
         *            Name of the property.
         * @return {@link Comparator} of {@link EObject} which compares on basis of given property.
         * @throws ClassCastException
         *             when value returned by given property is not {@link Comparable}
         */
        public static Comparator< EObject > property( String aProperty )
        {
            return Comparator.comparing( Functions.value( aProperty ) );
        }

        /**
         * Comparator base on properties with given name.
         * 
         * @param aProperty
         *            Name of the property.
         * @param aClass
         *            Class to perform type safe check.
         * @return {@link Comparator} of {@link EObject} which compares on basis of given property.
         * @throws ClassCastException
         *             when value returned by given property is not {@link Comparable}
         */
        public static < T extends Comparable< T >>Comparator< EObject > property( String aProperty,
            Class< T > aClass )
        {
            return Comparator.comparing( Functions.value( aProperty, aClass ) );
        }
    }

}
