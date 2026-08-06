package net.client.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.*;

public class ClientTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (bytes == null) return null;

        if (transformedName.equals("net.minecraft.client.Minecraft")) {
            return transformMinecraft(bytes);
        } else if (transformedName.equals("net.minecraft.client.renderer.EntityRenderer")) {
            return transformEntityRenderer(bytes);
        }

        return bytes;
    }

    private byte[] transformMinecraft(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
        cr.accept(new ClassVisitor(Opcodes.ASM5, cw) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
                // MCP: runTick | Notch: s
                if ((name.equals("runTick") || name.equals("s")) && desc.equals("()V")) {
                    return new MethodVisitor(Opcodes.ASM5, mv) {
                        @Override
                        public void visitCode() {
                            super.visitCode();
                            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "net/client/asm/Hooks", "onTick", "()V", false);
                        }
                    };
                }
                return mv;
            }
        }, 0);
        return cw.toByteArray();
    }

    private byte[] transformEntityRenderer(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
        cr.accept(new ClassVisitor(Opcodes.ASM5, cw) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
                // MCP: updateCameraAndRender | Notch: a
                if ((name.equals("updateCameraAndRender") || name.equals("a")) && desc.equals("(FJ)V")) {
                    return new MethodVisitor(Opcodes.ASM5, mv) {
                        @Override
                        public void visitCode() {
                            super.visitCode();
                            mv.visitVarInsn(Opcodes.FLOAD, 1);
                            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "net/client/asm/Hooks", "onRender", "(F)V", false);
                        }
                    };
                }
                return mv;
            }
        }, 0);
        return cw.toByteArray();
    }
}
