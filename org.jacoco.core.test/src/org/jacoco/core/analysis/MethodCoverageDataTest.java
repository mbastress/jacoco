/*******************************************************************************
 * Copyright (c) 2009 Mountainminds GmbH & Co. KG and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Marc R. Hoffmann - initial API and implementation
 *    
 * $Id: $
 *******************************************************************************/
package org.jacoco.core.analysis;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for {@link MethodNode}.
 * 
 * @author Marc R. Hoffmann
 * @version $Revision: $
 */
public class MethodCoverageDataTest {

	@Test
	public void testProperties() {
		// Example: java.util.Collections.emptySet()
		MethodNode data = new MethodNode("emptySet", "()Ljava/util/Set;",
				"<T:Ljava/lang/Object;>()Ljava/util/Set<TT;>;");
		assertEquals(ICoverageDataNode.ElementType.METHOD, data
				.getElementType());
		assertEquals("emptySet", data.getName());
		assertEquals("()Ljava/util/Set;", data.getDesc());
		assertEquals("<T:Ljava/lang/Object;>()Ljava/util/Set<TT;>;", data
				.getSignature());
	}

	@Test
	public void testEmptyMethod() {
		ICoverageDataSummary data = new MethodNode("sample", "()V", null);
		assertEquals(0, data.getInstructionCounter().getTotalCount(), 0.0);
		assertEquals(0, data.getInstructionCounter().getCoveredCount(), 0.0);
		assertEquals(0, data.getBlockCounter().getTotalCount(), 0.0);
		assertEquals(0, data.getBlockCounter().getCoveredCount(), 0.0);
		assertEquals(1, data.getMethodCounter().getTotalCount(), 0.0);
		assertEquals(0, data.getMethodCounter().getCoveredCount(), 0.0);
		assertEquals(0, data.getClassCounter().getTotalCount(), 0.0);
		assertEquals(0, data.getClassCounter().getCoveredCount(), 0.0);
	}

	@Test
	public void testNotCovered() {
		MethodNode data = new MethodNode("sample", "()V", null);
		data.addBlock(5, new int[0], false);
		data.addBlock(8, new int[0], false);
		assertEquals(13, data.getInstructionCounter().getTotalCount(), 0.0);
		assertEquals(0, data.getInstructionCounter().getCoveredCount(), 0.0);
		assertEquals(2, data.getBlockCounter().getTotalCount(), 0.0);
		assertEquals(0, data.getBlockCounter().getCoveredCount(), 0.0);
		assertEquals(1, data.getMethodCounter().getTotalCount(), 0.0);
		assertEquals(0, data.getMethodCounter().getCoveredCount(), 0.0);
		assertEquals(0, data.getClassCounter().getTotalCount(), 0.0);
		assertEquals(0, data.getClassCounter().getCoveredCount(), 0.0);
	}

	@Test
	public void testCovered() {
		MethodNode data = new MethodNode("sample", "()V", null);
		data.addBlock(5, new int[0], true);
		data.addBlock(8, new int[0], false);
		assertEquals(13, data.getInstructionCounter().getTotalCount(), 0.0);
		assertEquals(5, data.getInstructionCounter().getCoveredCount(), 0.0);
		assertEquals(2, data.getBlockCounter().getTotalCount(), 0.0);
		assertEquals(1, data.getBlockCounter().getCoveredCount(), 0.0);
		assertEquals(1, data.getMethodCounter().getTotalCount(), 0.0);
		assertEquals(1, data.getMethodCounter().getCoveredCount(), 0.0);
		assertEquals(0, data.getClassCounter().getTotalCount(), 0.0);
		assertEquals(0, data.getClassCounter().getCoveredCount(), 0.0);
	}

}